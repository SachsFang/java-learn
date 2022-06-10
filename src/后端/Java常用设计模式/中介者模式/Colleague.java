package 后端.Java常用设计模式.中介者模式;

/**
 * 抽象同事类
 * @author shaobin
 * @date 2022/6/10 17:34
 */
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
