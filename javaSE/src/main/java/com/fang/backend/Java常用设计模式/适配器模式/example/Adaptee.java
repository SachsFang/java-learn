package com.fang.backend.Java常用设计模式.适配器模式.example;

/**
 * 需要适配的类
 * @author shaobin
 * @date 2022/4/19 16:53
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("特殊请求");
    }
}
