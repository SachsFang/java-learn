package com.fang.rabbitmq;

import com.fang.rabbitmq.pojo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2023/2/22 14:20
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/sendMqMsg")
    @ResponseBody
    public String sendMqMsg() {
        User user = new User();
        user.setName("发送消息成功");
        user.setAge(25);
        user.setSex(1);
        amqpTemplate.convertAndSend(RabbitMqConfig.SPRINGBOOT_EXCHANGE, "", user, message -> {
            // 设置消息5秒过期,过期后给死信队列消费
            message.getMessageProperties().setExpiration("10000");
            return message;
        });
        return "ok";
    }
}
