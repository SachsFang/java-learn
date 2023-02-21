package com.fang.springboot.rabbit_mq.publish_subscribe_message_model.fanout;

import com.fang.springboot.rabbit_mq.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/21 11:14
 */
public class SecondConsumer {

    public static final String QUEUE_FIRST_NAME = "second_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // fanout exchange
//        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "");
        // direct exchange
//        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "second");
        // topic exchange
        channel.queueBind(QUEUE_FIRST_NAME, Producer.EXCHANGE_NAME, "second.*");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2获取消息并消费：" + new String(body, StandardCharsets.UTF_8));
                try {
                    Thread.sleep(1000); //业务逻辑代码模拟
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        channel.basicConsume(QUEUE_FIRST_NAME, false, defaultConsumer);
    }
}
