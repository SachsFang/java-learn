package com.fang.springboot.rabbit_mq.base_or_work_message_model;

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
        for (int i = 0; i < 10; i++) {
            String msg = (i + 1) + " msg";
            Producer producer = new Producer();
            // 普通方式发送
//            producer.sendMsg(msg, channel);
            // 消息确认机制发送
//            producer.sendMsgByConfirm(msg, channel);
            // 事务形式发送
            producer.sendMsgByTransactional(msg, channel);
        }
        // 关闭连接
        channel.close();
        connection.close();
    }

    /**
     * 普通方式发送消息
     */
    public void sendMsg(String msg, Channel channel) throws IOException, InterruptedException {
        channel.basicPublish("", RabbitMqUtil.QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
        System.out.println("生产者向MQ服务端投递了一条消息：" + msg);
        Thread.sleep(10);
    }

    /**
     * 使用消息确认机制发送消息
     */
    public void sendMsgByConfirm(String msg, Channel channel) throws IOException, InterruptedException {
        channel.confirmSelect();
        channel.basicPublish("", RabbitMqUtil.QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
        if (channel.waitForConfirms()) {
            Thread.sleep(10); // 模拟业务代码
            int error = 1 / 0;// 模拟业务代码出错
            System.out.println("生产者向MQ服务端投递了一条消息：" + msg);
        } else {
            System.out.println("消息投递失败");
        }
    }

    /**
     * 使用事务方式发送消息
     */
    public void sendMsgByTransactional(String msg, Channel channel) throws IOException, InterruptedException {
        try {
            channel.txSelect();
            channel.basicPublish("", RabbitMqUtil.QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
            Thread.sleep(10); // 模拟业务代码
            int error = 1 / 0;// 模拟业务代码出错
            System.out.println("生产者向MQ服务端投递了一条消息：" + msg);
            channel.txCommit();
        } catch (Exception e) {
            if (channel != null) {
                channel.txRollback();
            }
        }
    }
}
