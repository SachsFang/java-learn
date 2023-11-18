package com.fang.backend.接口的学习;

/**
 * Created by SachsFang on 2021/5/8 20:45
 */
public class MyCar implements defaultInterface.Car, defaultInterface.Bus {
    @Override
    public void say() {
        System.out.println("do something");
        defaultInterface.Car.super.say();
        defaultInterface.Bus.super.say();
        defaultInterface.Car.speak();
    }


}
