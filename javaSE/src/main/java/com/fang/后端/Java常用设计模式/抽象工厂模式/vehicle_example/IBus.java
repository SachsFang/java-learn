package com.fang.后端.Java常用设计模式.抽象工厂模式.vehicle_example;

/**
 * 公共汽车接口
 * 让不同的品牌去实现
 * @author shaobin
 * @date 2022/4/16 13:17
 */
public interface IBus {
    String busName = "私家车";

    String makeInfo();
}
