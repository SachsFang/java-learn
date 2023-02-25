package com.fang.springboot.rabbitmq_consumer_one;

import com.fang.rabbitmq.pojo.User;
import com.fang.springboot.rabbitmq_consumer_one.dao.OrderDAO;
import com.fang.springboot.rabbitmq_consumer_one.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2023/2/22 9:35
 */
@Slf4j
@Configuration
@RabbitListener(queues = "first_queue")
public class ConsumerOne {

    @Autowired
    private OrderDAO orderDAO;

    @RabbitHandler
    public void process(User user) {
        log.info("消费者1接收消息成功：" + user);
        Order order = new Order();
        order.setCode("rabbitmq-test");
        order.setAmount(1);
        order.setProductId("001");
        orderDAO.insertOrder(order);
        int i = 1 / 0;
    }

}
