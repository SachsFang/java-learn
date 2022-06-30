package 后端.Java常用设计模式.解释器模式.base_example;

/**
 * @author shaobin
 * @date 2022/6/29 11:03
 */
public abstract class AbstractExpression {
    abstract void interpret(Context context);
}
