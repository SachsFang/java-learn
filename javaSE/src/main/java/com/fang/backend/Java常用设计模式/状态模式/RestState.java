package com.fang.backend.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/18 17:51
 */
public class RestState implements State {
    @Override
    public void writeProgram(WorkContext workContext) {
        System.out.println("休息状态，回家！");
    }
}
