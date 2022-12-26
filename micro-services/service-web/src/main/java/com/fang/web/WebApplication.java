package com.fang.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/26 10:17
 */
@SpringBootApplication
@ComponentScan({"com.fang.web", "com.fang.micro.api.order", "com.fang.micro.impl.order"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
    }
}
