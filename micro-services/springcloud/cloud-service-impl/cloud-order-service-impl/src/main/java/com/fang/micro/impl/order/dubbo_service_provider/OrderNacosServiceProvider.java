package com.fang.micro.impl.order.dubbo_service_provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/27 15:16
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 不使用自动配置数据源，采用手动配置，因为seata和mybatis有冲突
@ComponentScan({"com.fang.micro.impl.order"})
@EnableDiscoveryClient //开启服务注册和发现功能
public class OrderNacosServiceProvider {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(OrderNacosServiceProvider.class);
        springApplication.run(args);
    }
}
