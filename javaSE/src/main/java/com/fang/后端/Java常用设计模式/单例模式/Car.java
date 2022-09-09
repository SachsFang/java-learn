package com.fang.后端.Java常用设计模式.单例模式;

/**
 * Created by SachsFang on 2021/7/14 20:14
 * 饿汉式单例
 */
public class Car {

    private static Car car = new Car();

    private Car() {}

    public static Car getInstance(){
        return car;
    }
}
