package com.fang.backend.Java常用设计模式.中介者模式;

/**
 * 抽象中介者
 * @author shaobin
 * @date 2022/6/10 17:37
 */
public abstract class Mediator {
    /**
     * send message
     * @param message
     * @param colleague
     */
    public abstract void send(String message, Colleague colleague);
}
