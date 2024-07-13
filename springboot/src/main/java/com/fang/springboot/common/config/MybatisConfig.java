package com.fang.springboot.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/11/27 15:26
 */
@Configuration
@MapperScan({"com.fang.springboot.*.dao", "com.fang.springboot.common.functions_module.*.dao"})
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new MyBatisConfigurationCustomizer();
    }

    private static class MyBatisConfigurationCustomizer implements ConfigurationCustomizer {
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            // 配置 MyBatis 其他设置，如果有的话
            // todo后续可以实现通用dao，目前还没看过源码
        }
    }

}

