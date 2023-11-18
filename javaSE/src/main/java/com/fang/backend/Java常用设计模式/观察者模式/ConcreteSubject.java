package com.fang.backend.Java常用设计模式.观察者模式;

/**
 * @author shaobin
 * @date 2022/4/15 11:18
 */
public class ConcreteSubject extends Subject {
    private String name;

    ConcreteSubject(String name) {
        this.name = name;
    }
}
