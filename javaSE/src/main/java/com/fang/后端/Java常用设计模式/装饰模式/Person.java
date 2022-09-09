package com.fang.后端.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 16:32
 * 穿各种中衣服
 */
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("衣服搭配的" + this.name);
    }
}
