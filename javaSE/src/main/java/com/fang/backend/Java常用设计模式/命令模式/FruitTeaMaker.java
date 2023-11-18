package com.fang.backend.Java常用设计模式.命令模式;

/**
 * @author shaobin
 * @date 2022/5/23 14:44
 */
public class FruitTeaMaker extends TeaMaker {
    @Override
    public void make() {
        System.out.println("制作水果茶");
    }
}
