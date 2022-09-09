package com.fang.后端.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/18 17:32
 */
public class AfternoonState implements State {
    @Override
    public void writeProgram(WorkContext workContext) {
        if (workContext.getHour() < 18) {
            System.out.println("现在是下午上班状态，精神还行");
        } else {
            workContext.setState(new EveningState());
            workContext.writeProgram();
        }
    }
}
