package com.fang.后端.Java常用设计模式.简单工厂模式.vehicle_example;

/**
 * @author shaobin
 * @date 2022/4/16 9:20
 */
public class BenzCar implements ICar {

    @Override
    public String makeInfo() {
        return "奔驰制造的" + carName;
    }
}
