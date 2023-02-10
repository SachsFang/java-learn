package com.fang.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2022/12/26 10:17
 */
@SpringBootApplication
@EnableDubbo
public class DubboWebRunApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DubboWebRunApplication.class);
        springApplication.run(args);
    }
}
