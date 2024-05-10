package com.fang.springboot.common.functions_module.cache_query.configuration;

import com.fang.springboot.common.functions_module.cache_query.aspect.CacheQueryAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FireShareWebMvcAutoConfiguration {

    @Bean
    public CacheQueryAspect cacheQueryAspect() {
        return new CacheQueryAspect();
    }

}