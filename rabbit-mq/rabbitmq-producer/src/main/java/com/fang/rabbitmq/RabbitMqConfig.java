package com.fang.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaobin
 * @date 2023/2/21 18:08
 */
@Configuration
public class RabbitMqConfig {

    /**
     * rabbitmq中若没有对应的交换机或队列名称，则在请求使用过程中会自动创建，而不是在启动时创建
     */
    public static String SPRINGBOOT_EXCHANGE = "fanout_exchange";

    public static String DEAD_EXCHANGE = "dead_exchange";

    public static String FIRST_QUEUE = "first_queue";

    public static String SECOND_QUEUE = "second_queue";

    /**
     * 死信队列
     */
    public static String DEAD_QUEUE = "dead_queue";

    public static String DLX_ROUTING_KEY = "dlx";

    @Bean
    public FanoutExchange springbootExchange() {
        return new FanoutExchange(SPRINGBOOT_EXCHANGE);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean
    public Queue firstQueue() {
        // 第一个普通队列绑定死信交换机
        Map<String, Object> arguments = getDeadExchangeArguments();
        return new Queue(FIRST_QUEUE, true, false, false, arguments);
    }

    @Bean
    public Queue secondQueue() {
        Map<String, Object> arguments = getDeadExchangeArguments();
        return new Queue(SECOND_QUEUE, true, false, false, arguments);
    }

    private Map<String, Object> getDeadExchangeArguments() {
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return arguments;
    }

    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE);
    }

    @Bean
    public Binding bindingFirstQueueToExchange(Queue firstQueue, FanoutExchange springbootExchange) {
        return BindingBuilder.bind(firstQueue).to(springbootExchange);
    }

    @Bean
    public Binding bindingSecondQueueToExchange(Queue secondQueue, FanoutExchange springbootExchange) {
        return BindingBuilder.bind(secondQueue).to(springbootExchange);
    }

    /**
     * 死信队列绑定信息交换机
     */
    @Bean
    public Binding bindingDeadQueueToExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DLX_ROUTING_KEY);
    }

}
