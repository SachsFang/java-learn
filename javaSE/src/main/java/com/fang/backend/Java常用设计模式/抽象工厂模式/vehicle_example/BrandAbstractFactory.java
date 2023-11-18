package com.fang.backend.Java常用设计模式.抽象工厂模式.vehicle_example;

import com.fang.backend.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * 交通工具品牌抽象接口工厂
 * 对应工厂方法模式的 VehicleCreator
 * @author shaobin
 * @date 2022/4/15 18:07
 */
interface BrandAbstractFactory {

    /**
     * 公共汽车
     */
    IBus createBus();

    /**
     * 私家车
     */
    ICar createCar();

}
