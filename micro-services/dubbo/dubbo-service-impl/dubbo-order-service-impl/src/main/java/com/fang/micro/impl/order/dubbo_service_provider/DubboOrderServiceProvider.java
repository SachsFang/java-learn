package com.fang.micro.impl.order.dubbo_service_provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/27 15:16
 */
@SpringBootApplication
@ComponentScan({"com.fang.micro.impl.order"})
@EnableDubbo(scanBasePackages = {"com.fang.micro.impl.order"})
public class DubboOrderServiceProvider {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DubboOrderServiceProvider.class);
        springApplication.run(args);
    }
}
