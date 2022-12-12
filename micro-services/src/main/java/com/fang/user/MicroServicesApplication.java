package com.fang.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2022/11/26 19:40
 */
@SpringBootApplication
public class MicroServicesApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MicroServicesApplication.class);
        springApplication.run(args);
    }
}
