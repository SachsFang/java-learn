package com.fang.springboot.common.functions_module.redis.utils;

import com.fang.springboot.common.functions_module.redis.service.RedisService;

/**
 * @author shaobin
 * @date 2024/5/10 16:40
 */
public class RedisCacheUtilsHelper {

    public static void setRedisService(RedisService redisService){
        RedisCacheUtils.REDIS_SERVICE = redisService;
    }

}
