package com.fang.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/26 10:17
 */
@SpringBootApplication
@ComponentScan({"com.fang.web", "com.fang.micro.api.order"})
public class WebRunApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebRunApplication.class);
        springApplication.run(args);
    }
}
