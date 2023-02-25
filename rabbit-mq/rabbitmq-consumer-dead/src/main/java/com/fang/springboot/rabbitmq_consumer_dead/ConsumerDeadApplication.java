package com.fang.springboot.rabbitmq_consumer_dead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaobin
 * @date 2023/2/22 9:45
 */
@SpringBootApplication
public class ConsumerDeadApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConsumerDeadApplication.class);
        springApplication.run(args);
    }
}
