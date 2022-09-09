package com.fang.后端.Java常用设计模式.适配器模式.example;

/**
 * @author shaobin
 * @date 2022/4/19 16:54
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        // 包装后可使用特殊请求
        target.request();
    }
}
