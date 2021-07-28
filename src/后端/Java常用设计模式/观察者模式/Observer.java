package 后端.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 11:59
 */
public abstract class Observer {
    public Subject subject;
    public abstract void update();
}
