package com.fang.springboot.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/11/27 15:26
 */
@Configuration
@MapperScan("com.fang.springboot.*.dao")
public class MybatisConfig {
}
