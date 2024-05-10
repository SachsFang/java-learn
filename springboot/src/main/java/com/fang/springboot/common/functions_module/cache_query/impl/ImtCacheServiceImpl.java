package com.fang.springboot.common.functions_module.cache_query.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fang.springboot.common.functions_module.cache_query.api.ImtCacheService;
import com.fang.springboot.common.functions_module.cache_query.dao.ImtCacheDAO;
import com.fang.springboot.common.functions_module.cache_query.pojo.ImtCacheDO;
import com.fang.springboot.common.util.JsonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 缓存中间表服务实现
 *
 * @author shaobin
 * @date 2024/5/6
 */
@Service
public class ImtCacheServiceImpl implements ImtCacheService {

    @Autowired
    private ImtCacheDAO imtCacheDAO;

    @Override
    public ImtCacheDO query(String methodPath, String params) {
        if (StringUtils.isBlank(methodPath) || StringUtils.isBlank(params)) {
            return null;
        }
        return imtCacheDAO.selectOne(Wrappers.<ImtCacheDO>lambdaQuery()
                .eq(ImtCacheDO::getMethodPath, methodPath)
                .eq(ImtCacheDO::getParams, params)
        );
    }

    @Override
    public ImtCacheDO saveOrUpdate(String methodPath, String params, Object data) {
        if (StringUtils.isBlank(methodPath) || StringUtils.isBlank(params) || Objects.isNull(data)) {
            return null;
        }
        return saveOrUpdate(new ImtCacheDO(methodPath, params, JsonUtil.parseToJson(data), new Date()));
    }

    @Override
    public ImtCacheDO saveOrUpdate(ImtCacheDO imtCacheDO) {
        imtCacheDAO.insert(imtCacheDO);
        return imtCacheDO;
    }

    @Override
    public boolean delete(List<String> methodPaths) {
        if (CollectionUtils.isEmpty(methodPaths)) {
            return false;
        }
        return imtCacheDAO.delete(Wrappers.<ImtCacheDO>lambdaQuery()
                .in(ImtCacheDO::getMethodPath, methodPaths)
        ) > 0;
    }
}