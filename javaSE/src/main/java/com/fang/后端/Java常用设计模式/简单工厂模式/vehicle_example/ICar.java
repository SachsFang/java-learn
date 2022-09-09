package com.fang.后端.Java常用设计模式.简单工厂模式.vehicle_example;

/**
 * 私家车接口
 * 让不同品牌去实现
 * @author shaobin
 * @date 2022/4/16 9:16
 */
public interface ICar {

    String carName = "私家车";

    String makeInfo();
}
