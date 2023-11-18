package com.fang.backend.Java常用设计模式.建造者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * Computer
 * @description computer is a product
 * @author shaobin
 * @date 2022/4/14 17:48
 */
public class Computer {

    List<String> parts;

    Computer() {
        parts = new ArrayList();
    }

    public void addPart(String part) {
        parts.add(part);
    }

    public String getInfo() {
        return parts.toString();
    }

}
