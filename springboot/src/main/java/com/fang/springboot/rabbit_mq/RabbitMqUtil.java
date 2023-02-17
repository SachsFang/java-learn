package com.fang.springboot.rabbit_mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/16 18:37
 */
public class RabbitMqUtil {

    public static final String QUEUE_NAME = "springboot-default";

    public static Connection getConnection() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接地址
        connectionFactory.setHost("127.0.0.1");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置用户名和密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 设置 VirtualHost
        connectionFactory.setVirtualHost("/springboot");
        return connectionFactory.newConnection();
    }

}
