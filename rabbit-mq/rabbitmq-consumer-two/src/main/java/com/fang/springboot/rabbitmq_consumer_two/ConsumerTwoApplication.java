package com.fang.springboot.rabbitmq_consumer_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2023/2/22 15:19
 */
@SpringBootApplication
public class ConsumerTwoApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConsumerTwoApplication.class);
        springApplication.run(args);
    }
}