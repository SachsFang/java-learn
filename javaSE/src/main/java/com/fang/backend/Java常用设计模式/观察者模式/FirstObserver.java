package com.fang.backend.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 11:59
 */
public class FirstObserver extends Observer {

    private String name;

    public FirstObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println("FirstObserver " + this.name + " already update");
    }
}
