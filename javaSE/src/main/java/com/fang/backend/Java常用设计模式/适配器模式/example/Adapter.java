package com.fang.backend.Java常用设计模式.适配器模式.example;

/**
 * 适配器
 * @author shaobin
 * @date 2022/4/19 16:51
 */
public class Adapter implements Target {

    Adaptee adaptee;

    public Adapter() {
        this.adaptee = new Adaptee();
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
