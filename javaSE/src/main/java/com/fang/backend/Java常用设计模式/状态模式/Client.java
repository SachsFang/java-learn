package com.fang.backend.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/19 12:05
 */
public class Client {
    public static void main(String[] args) {
        /* 状态模式例子： State 在不同条件下会有不同的行为 */
        WorkContext workContext = new WorkContext();
        // 初始化早上状态
        State state = new ForenoonState();
        workContext.setHour(10);
        state.writeProgram(workContext);

        workContext.setHour(13);
        state.writeProgram(workContext);

        workContext.setHour(16);
        state.writeProgram(workContext);

        workContext.setHour(19);
        workContext.setWorkFinish(false);
        state.writeProgram(workContext);

        workContext.setHour(24);
        state.writeProgram(workContext);

        workContext.setHour(19);
        workContext.setWorkFinish(true);
        state.writeProgram(workContext);
    }
}
