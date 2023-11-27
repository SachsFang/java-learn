package com.fang.springboot.common.config;

import com.fang.springboot.common.format.datetime.SmartDateFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/11/27 17:47
 */
@Configuration
public class AutoConfig {
    @Bean
    @ConditionalOnMissingBean
    SmartDateFormatter smartDateFormatter() {
        return new SmartDateFormatter();
    }
}
