package com.fang.spring.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shaobin
 * @date 2022/9/30 16:31
 */
@Configuration
//开启事务
@EnableTransactionManagement
@MapperScan("com.fang.spring.**.mapper")
@ComponentScan({"com.fang.spring.**.serviceImpl", "com.fang.spring.**.daoImpl", "com.fang.spring.**.config", "com.fang.spring.**.aop"})
//激活aop代理
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {
}
