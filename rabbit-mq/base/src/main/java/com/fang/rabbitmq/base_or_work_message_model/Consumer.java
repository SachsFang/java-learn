package com.fang.rabbitmq.base_or_work_message_model;

import com.fang.rabbitmq.RabbitMqUtil;
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
                    // 如果设置了autoAsk=true的情况下接收到消息直接确认给队列删除消息，所以并不会以下的业务逻辑代码
                    // 如果设置了autoAsk=false的情况下，需要发送ask请求给服务器端确认消费完成然后删除队列中的消息，所以需要走完一下的业务逻辑代码，便可处理消费失败的情况
                    Thread.sleep(1000); //业务逻辑代码模拟
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 设置监听的队列
        // autoAck：true为自动签收，false为手动应答（消费者获取到消息成功后就自动ack，服务端的队列就会自动删除；因为设置为true无法处理消费者消费失败的情况，所以在实际开发中一般设为false，待消费者成功消费后手动ack）
        // autoAck为true时，消息模型为基本消息模型（点对点）；为false时，消息模型为工作队列
//        channel.basicConsume(RabbitMqUtil.QUEUE_NAME, true, defaultConsumer);
        channel.basicConsume(RabbitMqUtil.QUEUE_NAME, false, defaultConsumer);
    }
}
