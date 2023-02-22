package com.fang.springboot.rabbitmq_consumer_two;

import com.fang.rabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/2/22 15:17
 */
@Slf4j
@Configuration
@RabbitListener(queues = "second_queue")
public class ConsumerTwo {

    @RabbitHandler
    public void process(User user) throws InterruptedException {
        Thread.sleep(1000);
        log.info("消费者2接收消息成功：" + user);
    }

}
