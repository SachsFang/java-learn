package com.fang.后端.Java常用设计模式.抽象工厂模式.vehicle_example;

import com.fang.后端.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * @author shaobin
 * @date 2022/4/16 13:26
 */
public class Client {
    public static void main(String[] args) {
        // （选择通用）想用哪个产品族就使用哪个工厂，一般一个系统只用一个产品族
        BenzFactory factory = new BenzFactory();
        // 系统中使用就可以隔离了品牌的概念，无需关心品牌，在系统初始化时就确定系统使用的品牌
        IBus bus = factory.createBus();
        ICar car = factory.createCar();
        System.out.println(bus.makeInfo());
        System.out.println(car.makeInfo());
    }
}
