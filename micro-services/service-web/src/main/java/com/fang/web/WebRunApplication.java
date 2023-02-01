package com.fang.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaobin
 * @date 2022/12/26 10:17
 */
@SpringBootApplication
//@ComponentScan({"com.fang.web"})
@EnableFeignClients //开启feign client
public class WebRunApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebRunApplication.class);
        springApplication.run(args);
    }
}
