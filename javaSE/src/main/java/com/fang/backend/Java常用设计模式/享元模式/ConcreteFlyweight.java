package com.fang.backend.Java常用设计模式.享元模式;

/**
 * @author shaobin
 * @date 2022/6/24 16:15
 */
public class ConcreteFlyweight extends Flyweight {

    private String str;

    ConcreteFlyweight(String str) {
        this.str = str;
    }

    @Override
    void operation() {
        System.out.println("Flyweight:" + str);
    }
}
