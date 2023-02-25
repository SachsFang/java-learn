package com.fang.springboot.rabbitmq_consumer_dead;

import com.fang.rabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/2/22 9:35
 */
@Slf4j
@Configuration
@RabbitListener(queues = "dead_queue")
public class ConsumerDead {

    @RabbitHandler
    public void process(User user) {
        log.info("死信消费者接收消息成功：" + user);
    }

}
