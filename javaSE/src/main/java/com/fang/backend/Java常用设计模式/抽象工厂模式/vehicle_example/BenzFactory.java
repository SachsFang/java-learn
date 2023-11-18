package com.fang.backend.Java常用设计模式.抽象工厂模式.vehicle_example;

import com.fang.backend.Java常用设计模式.简单工厂模式.vehicle_example.BenzCar;
import com.fang.backend.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * 奔驰交通工具工厂 - 奔驰品牌产品族
 * @author shaobin
 * @date 2022/4/4 19:12
 */
public class BenzFactory implements BrandAbstractFactory {

    @Override
    public IBus createBus() {
        return new BenzBus();
    }

    @Override
    public ICar createCar() {
        return new BenzCar();
    }
}
