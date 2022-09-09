package com.fang.后端.Java常用设计模式.适配器模式.手机适配器;

/**
 * 插座类 - 只能使用220v
 * Created by SachsFang on 2021/7/15 17:40
 */
public class Socket {

    public void use220v() {
        System.out.println("使用220v电压");
    }
}
