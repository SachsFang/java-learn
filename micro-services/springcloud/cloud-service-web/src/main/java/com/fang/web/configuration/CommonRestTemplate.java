package com.fang.web.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author shaobin
 * @date 2022/12/28 21:08
 */
@Configuration
public class CommonRestTemplate {
    @Bean
    public RestTemplate restTemplateConfig() {
        return new RestTemplate();
    }
}
