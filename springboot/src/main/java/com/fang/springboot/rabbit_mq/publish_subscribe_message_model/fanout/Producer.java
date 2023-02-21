package com.fang.springboot.rabbit_mq.publish_subscribe_message_model.fanout;

import com.fang.springboot.rabbit_mq.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author shaobin
 * @date 2023/2/21 11:04
 */
public class Producer {

//    public static final String EXCHANGE_NAME = "fanout_exchange";
//    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // fanout exchange
//        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout", true);
        // direct exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // topic exchange
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String msg = "这是fanout exchange类型消息";
        for (int i = 0; i < 10; i++) {
            // fanout exchange
//            channel.basicPublish(EXCHANGE_NAME, "", null, (msg + (i + 1)).getBytes());
            // direct exchange
//            channel.basicPublish(EXCHANGE_NAME, "second", null, (msg + (i + 1)).getBytes());
            // topic exchange
            channel.basicPublish(EXCHANGE_NAME, "second.abc", null, (msg + (i + 1)).getBytes());
        }
        channel.close();
        connection.close();
    }
}
