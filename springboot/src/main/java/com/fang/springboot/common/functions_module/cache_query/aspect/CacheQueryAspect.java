package com.fang.springboot.common.functions_module.cache_query.aspect;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.fang.springboot.common.functions_module.cache_query.annotation.CacheQuery;
import com.fang.springboot.common.functions_module.cache_query.annotation.CacheRemove;
import com.fang.springboot.common.functions_module.cache_query.api.ImtCacheService;
import com.fang.springboot.common.functions_module.cache_query.enums.CacheLevel;
import com.fang.springboot.common.functions_module.cache_query.pojo.ImtCacheDO;
import com.fang.springboot.common.functions_module.redis.utils.RedisCacheUtils;
import com.fang.springboot.common.util.JsonUtil;
import com.fang.springboot.common.functions_module.multi_thread_calc.util.MultiThreadCalcUtilV3;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author shaobin
 * @date 2024/5/6 16:50
 */
@Aspect
@Slf4j
public class CacheQueryAspect {

    private final String SPLIT_NUM = "/";

    private final String POINT_NUM = ".";

    private final String CACHE_ERROR = "Cache service error!";

    private final String DATABASE_ERROR = "Database service error!";

    @Value("${spring.application.name:}")
    private String appId;

    @Value("${cache_query}")
    private Boolean cahceQueryEnable;

    @Autowired
    private ImtCacheService imtCacheService;

    @Pointcut("@annotation(com.fang.springboot.common.functions_module.cache_query.annotation.CacheQuery)")
    public void cacheQueryPointcut() {
    }

    @Pointcut("@annotation(com.fang.springboot.common.functions_module.cache_query.annotation.CacheRemove)")
    public void cacheRemovePointcut() {
    }

    @Around(value = "cacheQueryPointcut()")
    @SneakyThrows
    public Object cacheQueryAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheQuery annotation = method.getAnnotation(CacheQuery.class);
        CacheLevel cacheLevel = annotation.cacheLevel();
        if (!cahceQueryEnable || Objects.equals(cacheLevel, CacheLevel.NONE)) {
            return joinPoint.proceed();
        }
        // 获取方法签名
        String methodPathKey = method.getDeclaringClass().getName() + POINT_NUM + method.getName();
        Type returnType = method.getGenericReturnType();
        StringBuilder argsKeyBuilder = new StringBuilder();
        // 获取方法参数值
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            argsKeyBuilder.append(arg.toString());
        }
        String argsKey = MD5.create().digestHex(argsKeyBuilder.toString());
        // 获取缓存数据
        Object data = null;
        String cacheData = null;
        if (StringUtils.isNotBlank(cacheData = getByCache(appId + methodPathKey, argsKey, cacheLevel))) {
            data = JSON.parseObject(Objects.toString(cacheData), returnType);
        } else if (Objects.nonNull(cacheData = getByImt(methodPathKey, argsKey, cacheLevel))) {
            data = JSON.parseObject(cacheData, returnType);
            String finalCacheData = cacheData;
            MultiThreadCalcUtilV3.async(() -> {
                putInCache(methodPathKey, argsKey, finalCacheData, cacheLevel);
            });
        } else {
            data = joinPoint.proceed();
            Object finalData = data;
            MultiThreadCalcUtilV3.async(() -> {
                putInCache(methodPathKey, argsKey, JsonUtil.parseToJson(finalData), cacheLevel);
                putInImt(methodPathKey, argsKey, finalData, cacheLevel);
            });
        }
        return data;
    }

    @AfterReturning(value = "cacheRemovePointcut()")
    @SneakyThrows
    public void CacheRemoveAfterReturning(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheRemove annotation = method.getAnnotation(CacheRemove.class);
        CacheLevel cacheLevel = annotation.cacheLevel();
        if (!cahceQueryEnable || Objects.equals(cacheLevel, CacheLevel.NONE)) {
            return;
        }
        String targetMethod = annotation.targetMethod();
        String[] targetMethods = annotation.targetMethods();
        List<String> targetMethodList = new ArrayList<>();
        if (Objects.nonNull(targetMethod)) {
            targetMethodList.add(targetMethod);
        }
        if (CollectionUtils.isNotEmpty(targetMethodList)) {
            targetMethodList.addAll(Arrays.asList(targetMethods));
        }
        targetMethodList.forEach(methodPath -> {
            removeCache(methodPath, cacheLevel);
        });
        removeImt(targetMethodList, cacheLevel);
    }

    private String getByImt(String methodPathKey, String argsKey, CacheLevel cacheLevel) {
        if (!isImtLevel(cacheLevel)) {
            return null;
        }
        String data = null;
        try {
            ImtCacheDO imtCacheDO = imtCacheService.query(methodPathKey, argsKey);
            if (Objects.nonNull(imtCacheDO)) {
                data = imtCacheDO.getData();
            }
        } catch (Exception e) {
            log.info(DATABASE_ERROR);
        }
        return data;
    }

    private void putInImt(String methodPathKey, String argsKey, Object data, CacheLevel cacheLevel) {
        if (!isImtLevel(cacheLevel)) {
            return;
        }
        try {
            imtCacheService.saveOrUpdate(this.appId, methodPathKey, argsKey, data);
        } catch (Exception e) {
            log.info(DATABASE_ERROR);
        }
    }

    private void removeImt(List<String> methodPathKeys, CacheLevel cacheLevel) {
        if (!isImtLevel(cacheLevel)) {
            return;
        }
        try {
            imtCacheService.delete(this.appId, methodPathKeys);
        } catch (Exception e) {
            log.info(DATABASE_ERROR);
        }
    }

    private String getByCache(String methodPathKey, String argsKey, CacheLevel cacheLevel) {
        if (!isCacheLevel(cacheLevel)) {
            return null;
        }
        String key = this.appId + SPLIT_NUM + methodPathKey + SPLIT_NUM + argsKey;
        String data = null;
        try {
            data = RedisCacheUtils.get(key, String.class);
        } catch (Exception e) {
            log.info(CACHE_ERROR);
        }
        return data;
    }

    private void putInCache(String methodPathKey, String argsKey, String data, CacheLevel cacheLevel) {
        if (!isCacheLevel(cacheLevel)) {
            return;
        }
        try {
            String key = this.appId + SPLIT_NUM + methodPathKey + SPLIT_NUM + argsKey;
            RedisCacheUtils.put(key, data, 24 * 60 * 60);
        } catch (Exception e) {
            log.info(CACHE_ERROR);
        }
    }

    private void removeCache(String methodPathKey, CacheLevel cacheLevel) {
        if (!isCacheLevel(cacheLevel)) {
            return;
        }
        try {
            Set<Serializable> keys = RedisCacheUtils.keys("*" + methodPathKey + "*");
            keys.forEach(key -> {
                RedisCacheUtils.remove(Objects.toString(key));
            });
        } catch (Exception e) {
            log.info(CACHE_ERROR);
        }
    }

    private boolean isImtLevel(CacheLevel cacheLevel) {
        return Objects.equals(cacheLevel, CacheLevel.IMT) || Objects.equals(cacheLevel, CacheLevel.MIXED);
    }

    private boolean isCacheLevel(CacheLevel cacheLevel) {
        return Objects.equals(cacheLevel, CacheLevel.CACHE) || Objects.equals(cacheLevel, CacheLevel.MIXED);
    }
}
