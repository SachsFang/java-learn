package com.fang.rabbitmq.publish_subscribe_message_model;

import com.fang.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/21 11:13
 */
public class FirstConsumer {

    public static final String QUEUE_FIRST_NAME = "first_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 消费者需要绑定exchange和queue的关系后，对应queue才能收到生产者发送的消息
        // fanout exchange
//        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "");
        // direct exchange
//        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "first");
        // topic exchange
        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "first.#");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1获取消息并消费：" + new String(body, StandardCharsets.UTF_8));
                try {
                    Thread.sleep(1000); //业务逻辑代码模拟
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        channel.basicConsume(QUEUE_FIRST_NAME, true, defaultConsumer);
    }
}
