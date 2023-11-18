package com.fang.backend.Java常用设计模式.状态模式;

/**
 * 中午状态
 * @author shaobin
 * @date 2022/4/18 17:28
 */
public class NoonState implements State {

    @Override
    public void writeProgram(WorkContext workContext) {
        if (workContext.getHour() < 14) {
            System.out.println("现在是中午休息状态，吃个饭睡个午觉先！");
        } else {
            workContext.setState(new AfternoonState());
            workContext.writeProgram();
        }
    }
}
