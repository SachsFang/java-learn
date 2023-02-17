package com.fang.springboot.rabbit_mq.example;

import com.fang.springboot.rabbit_mq.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/16 21:36
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接
        Connection connection = RabbitMqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 发送消息
        for (int i = 0; i < 300; i++) {
            String msg = (i + 1) + " msg";
            channel.basicPublish("", RabbitMqUtil.QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者向MQ服务端投递了一条消息：" + msg);
            Thread.sleep(10);
        }
        // 关闭连接
        channel.close();
        connection.close();
    }
}
