package com.fang.backend.Java常用设计模式.工厂方法模式.vehicle_example;

import com.fang.backend.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * @author shaobin
 * @date 2022/4/4 19:13
 */
public interface CarCreator {

    /**
     * 创建私家车
     * @return ICar
     */
     ICar createCar();

}
