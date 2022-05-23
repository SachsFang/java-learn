package 后端.Java常用设计模式.命令模式;

/**
 * 命令
 * @author shaobin
 * @date 2022/5/23 14:30
 */
public abstract class Command {
    public TeaMaker teaMaker;

    public TeaMaker getTeaMaker() {
        return teaMaker;
    }

    public void setTeaMaker(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
    }

    public void execute() {
        this.teaMaker.make();
    }
}
