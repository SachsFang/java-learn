package com.fang.springboot.common.functions_module.redis.config;

import com.fang.springboot.common.functions_module.redis.service.RedisService;
import com.fang.springboot.common.functions_module.redis.utils.RedisCacheUtilsHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author shaobin
 * @date 2024/5/10 16:37
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
public class RedisAutoConfig {

    @ConditionalOnMissingBean(name = "redisTemplate")
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);
        return template;
    }

    /**
     * {@link org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration}
     *
     * @param redisTemplate
     * @return
     */
    @ConditionalOnMissingBean(name = "redisService")
    @Bean("redisService")
    RedisService redisService(RedisTemplate redisTemplate) {
        RedisService redisService = new RedisService();
        redisService.setTemplate(redisTemplate);

        RedisCacheUtilsHelper.setRedisService(redisService);

        return redisService;
    }
}