package 后端.Java常用设计模式.状态模式;

/**
 * @author shaobin
 * @date 2022/4/18 17:25
 */
public class WorkContext {
    private int hour;
    private State state;
    private boolean workFinish;

    public boolean isWorkFinish() {
        return workFinish;
    }

    public void setWorkFinish(boolean workFinish) {
        this.workFinish = workFinish;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void writeProgram() {
        this.state.writeProgram(this);
    }
}
