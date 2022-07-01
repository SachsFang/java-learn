package 后端.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:22
 */
public abstract class Person {
    abstract public void accept(Status status);
}
