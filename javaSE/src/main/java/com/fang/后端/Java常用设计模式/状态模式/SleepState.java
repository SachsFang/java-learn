package com.fang.后端.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/19 12:03
 */
public class SleepState implements State {
    @Override
    public void writeProgram(WorkContext workContext) {
        System.out.println("睡着状态了，加班抽过21点了");
    }
}
