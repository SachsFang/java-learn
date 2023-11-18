package com.fang.backend.Java常用设计模式.享元模式;

/**
 * 享元模式 - 取对象时如果对象为null则创建一个，有则复用（享元）
 *
 * @author shaobin
 * @date 2022/6/24 16:30
 */
public class Client {
    public static void main(String[] args) {
        Flyweight obj1 = FlyweightFactory.get("汽车");
        Flyweight obj2 = FlyweightFactory.get("汽车");
        Flyweight obj3 = new UnsharedConcreteFlyweight("汽车");
        obj1.operation();
        obj2.operation();
        System.out.println(obj1 == obj2);
        System.out.println(obj1 == obj3);
    }
}
