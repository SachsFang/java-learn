package com.fang.springboot.common.functions_module.cache_query.api;

import com.fang.springboot.common.functions_module.cache_query.pojo.ImtCacheDO;
import com.fang.springboot.common.interfact.FacadeService;

import java.util.List;

/**
 * 缓存中间表服务
 *
 * @author shaobin
 * @date 2024/5/6
 */
public interface ImtCacheService extends FacadeService {
    /**
     * 查询缓存中间表数据
     *
     * @param methodPath
     * @param params
     * @return
     */
    ImtCacheDO query(String methodPath, String params);

    /**
     * 保存或更新缓存中间表数据
     *
     * @param methodPath
     * @param params
     * @param data
     * @return
     */
    ImtCacheDO saveOrUpdate(String methodPath, String params, Object data);

    /**
     * 保存或更新缓存中间表数据
     *
     * @param ImtCacheDO 缓存中间表数据
     * @return
     */
    ImtCacheDO saveOrUpdate(ImtCacheDO ImtCacheDO);

    /**
     * 根据方法路径删除数据
     *
     * @param methodPaths
     * @return
     */
    boolean delete(List<String> methodPaths);
}