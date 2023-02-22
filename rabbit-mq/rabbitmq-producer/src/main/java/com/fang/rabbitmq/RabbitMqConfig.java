package com.fang.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/2/21 18:08
 */
@Configuration
public class RabbitMqConfig {

    public static String SPRINGBOOT_EXCHANGE = "fanout_exchange";

    public static String FIRST_QUEUE = "first_queue";

    public static String SECOND_QUEUE = "second_queue";

    @Bean
    public FanoutExchange springbootExchange() {
        return new FanoutExchange(SPRINGBOOT_EXCHANGE);
    }

    @Bean
    public Queue firstQueue() {
        return new Queue(FIRST_QUEUE);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(SECOND_QUEUE);
    }

    @Bean
    public Binding bindingFirstQueueToExchange(Queue firstQueue, FanoutExchange springbootExchange) {
        return BindingBuilder.bind(firstQueue).to(springbootExchange);
    }

    @Bean
    public Binding bindingSecondQueueToExchange(Queue secondQueue, FanoutExchange springbootExchange) {
        return BindingBuilder.bind(secondQueue).to(springbootExchange);
    }

}
