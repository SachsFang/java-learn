package 后端.Java常用设计模式.观察者模式;

/**
 * 观察者
 * @author shaobin
 * @Date 2021/7/16 11:59
 */
public abstract class Observer {
    /**
     * 观察者名称
     */
    protected String name;
    /**
     * 观察对象
     */
    protected Subject subject;

    public Observer(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public abstract void update();
}
