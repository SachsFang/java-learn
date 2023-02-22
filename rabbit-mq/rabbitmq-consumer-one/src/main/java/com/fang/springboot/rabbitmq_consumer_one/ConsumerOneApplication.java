package com.fang.springboot.rabbitmq_consumer_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2023/2/22 9:45
 */
@SpringBootApplication
public class ConsumerOneApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConsumerOneApplication.class);
        springApplication.run(args);
    }
}
