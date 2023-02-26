package com.fang.springboot.rabbitmq_consumer_one;

import com.fang.rabbitmq.pojo.User;
import com.fang.springboot.rabbitmq_consumer_one.dao.OrderDAO;
import com.fang.springboot.rabbitmq_consumer_one.pojo.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional // 保证幂等性
    public void process(User user, Message message, Channel channel) {
        try {// 此处是为了防止代码因为bug出错，为了让因为网络不通抖动等情况导致的消费失败进行重试
            log.info("消费者1接收消息成功：" + user);
            Order order = new Order();
            order.setCode("rabbitmq-test");
            order.setAmount(1);
            order.setProductId("001");
            orderDAO.insertOrder(order);
//            int i = 1 / 0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 代码异常解决方案
            // 1.可以将日志存放起来，后期通过定时任务或者人工的形式进行补偿
            // 2.可以使用死信队列。在死信消费者中发布新的版本解决异常
        }
    }

}
