package com.fang.springboot.rabbit_mq.example;

import com.fang.springboot.rabbit_mq.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/17 10:15
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = RabbitMqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 接收到消息的处理方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者获取消息并消费：" + new String(body, StandardCharsets.UTF_8));
                try {
                    // 因为设置了autoAsk所以接收到消息直接确认给队列删除消息，所以后面的业务逻辑是在ask后执行的
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 设置监听的队列
        // autoAck：自动签收（消费者获取到消息成功后就自动ack，服务端的队列就会自动删除；因为设置为true无法处理消费者消费失败的情况，所以在实际开发中一般设为false，待消费者成功消费后手动ack）
        channel.basicConsume(RabbitMqUtil.QUEUE_NAME, true, defaultConsumer);
    }
}
