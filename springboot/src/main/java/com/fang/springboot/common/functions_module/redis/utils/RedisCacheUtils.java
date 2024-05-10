package com.fang.springboot.common.functions_module.redis.utils;

import com.fang.springboot.common.functions_module.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Set;

/**
 * @author shaobin
 * @date 2024/5/10 16:20
 */
public class RedisCacheUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheUtils.class);

    protected static RedisService REDIS_SERVICE;

    protected static RedisService getRedisService(){
        return REDIS_SERVICE;
    }

    /**
     * 添加缓存项
     * @param key
     * @param value
     */
    public static void put(final String key, final Serializable value){
        REDIS_SERVICE.put(key, value);
    }

    /**
     * 添加缓存项并指定过期时间
     * @param key
     * @param value
     * @param expiredDelay 单位为秒
     */
    public static void put(final String key, final Serializable value, final long expiredDelay) {
        REDIS_SERVICE.put(key, value, expiredDelay);
    }

    /**
     * 获得缓存项
     * @param key
     * @return
     */
    public static <T extends Serializable> T get(final String key, Class<T> c) {
        Serializable obj = REDIS_SERVICE.get(key);
        if(obj == null){
            return null;
        }else{
            return (T)obj;
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public static void remove(final String key) {
        REDIS_SERVICE.remove(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param expiredDelay 单位为秒
     */
    public static void expire(final String key, final long expiredDelay){
        REDIS_SERVICE.expire(key, expiredDelay);
    }

    public static <T extends Serializable> Set<T> keys(final String pattern){
        return (Set<T>)REDIS_SERVICE.keys(pattern);
    }
}
