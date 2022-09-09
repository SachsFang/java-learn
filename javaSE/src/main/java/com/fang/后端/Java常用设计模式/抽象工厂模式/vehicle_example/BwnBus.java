package com.fang.后端.Java常用设计模式.抽象工厂模式.vehicle_example;

/**
 * @author shaobin
 * @date 2022/4/16 13:20
 */
public class BwnBus implements IBus{

    @Override
    public String makeInfo() {
        return "宝马制造的" + busName;
    }
}
