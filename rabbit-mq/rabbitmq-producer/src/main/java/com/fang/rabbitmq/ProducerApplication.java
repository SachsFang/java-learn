package com.fang.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2023/2/22 11:42
 */
@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ProducerApplication.class);
        springApplication.run(args);
    }
}
