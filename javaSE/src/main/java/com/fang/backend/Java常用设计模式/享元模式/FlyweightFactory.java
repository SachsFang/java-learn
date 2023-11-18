package com.fang.backend.Java常用设计模式.享元模式;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author shaobin
 * @date 2022/6/24 16:16
 */
public class FlyweightFactory {

    private static HashMap<String, Flyweight> flyweightMap = new HashMap<String, Flyweight>();

    public static Flyweight get(String key) {
        if (Objects.isNull(flyweightMap.get(key))) {
            flyweightMap.put(key, new ConcreteFlyweight(key));
        }
        return flyweightMap.get(key);
    }
}
