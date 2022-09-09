package com.fang.后端.Java常用设计模式.中介者模式;

/**
 * @author shaobin
 * @date 2022/6/10 17:44
 */
public class Sachs extends Colleague{

    public void send(String message) {
        message = "Sachs发送了消息-" + message;
        this.mediator.send(message, this);
    }

    public void notify(String message) {
        System.out.println("Sachs收到了消息：" + message);
    }
}
