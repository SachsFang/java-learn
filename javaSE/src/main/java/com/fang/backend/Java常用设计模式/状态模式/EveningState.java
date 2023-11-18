package com.fang.backend.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/18 17:34
 */
public class EveningState implements State {
    @Override
    public void writeProgram(WorkContext workContext) {
        System.out.print("现在是下班");
        if (workContext.isWorkFinish()) {
            workContext.setState(new RestState());
            workContext.writeProgram();
        } else {
            if (workContext.getHour() < 21) {
                System.out.println("加班状态，工作没完成");
            } else {
                workContext.setState(new SleepState());
                workContext.writeProgram();
            }
        }
    }
}
