package com.fang.后端.Java常用设计模式.享元模式;

/**
 * @author shaobin
 * @date 2022/6/24 16:36
 */
public class UnsharedConcreteFlyweight extends Flyweight {

    private String str;

    public UnsharedConcreteFlyweight(String str) {
        this.str = str;
    }

    @Override
    void operation() {
        System.out.println("Unshared flyweight:" + str);
    }
}
