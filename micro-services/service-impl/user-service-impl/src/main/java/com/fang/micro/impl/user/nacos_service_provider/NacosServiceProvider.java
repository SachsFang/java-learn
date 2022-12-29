package com.fang.micro.impl.user.nacos_service_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/27 15:16
 */
@SpringBootApplication
@ComponentScan({"com.fang.micro.impl.user"})
@EnableDiscoveryClient //开启服务注册和发现功能
public class NacosServiceProvider {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(NacosServiceProvider.class);
        springApplication.run(args);
    }
}
