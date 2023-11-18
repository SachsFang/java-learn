package com.fang.backend.Java常用设计模式.命令模式;

/**
 * @author shaobin
 * @date 2022/5/23 14:46
 */
public class LemonTeaMaker extends TeaMaker {
    @Override
    public void make() {
        System.out.println("制作柠檬茶");
    }
}
