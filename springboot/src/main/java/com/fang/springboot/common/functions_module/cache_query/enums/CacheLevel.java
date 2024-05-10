package com.fang.springboot.common.functions_module.cache_query.enums;

public enum CacheLevel {
    /**
     * 不使用缓存
     */
    NONE,
    /**
     * 第三方缓存
     */
    CACHE,
    /**
     * 中间表缓存
     */
    IMT,
    /**
     * 第三方缓存和中间表缓存混合策略
     */
    MIXED
}