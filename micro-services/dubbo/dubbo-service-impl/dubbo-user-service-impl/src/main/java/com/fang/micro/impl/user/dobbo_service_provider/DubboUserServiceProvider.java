package com.fang.micro.impl.user.dobbo_service_provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/27 15:16
 */
@SpringBootApplication
@ComponentScan({"com.fang.micro.impl.user"})
@EnableDubbo(scanBasePackages = {"com.fang.micro.impl.user"})
public class DubboUserServiceProvider {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DubboUserServiceProvider.class);
        springApplication.run(args);
    }
}
