package com.fang.后端.Java常用设计模式.适配器模式.手机适配器;

/**
 * 手机类 - 只能使用5v
 * Created by SachsFang on 2021/7/15 17:30
 */
public class Phone {

    public void usePower5v() {
        System.out.println("使用5v电压");
    }
}
