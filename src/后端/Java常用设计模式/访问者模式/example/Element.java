package 后端.Java常用设计模式.访问者模式.example;

/**
 * 元素接口（被访问对象）
 *
 * @author shaobin
 * @date 2022/6/30 17:44
 */
public abstract class Element {
    /**
     * 接受访问方法
     * @param visitor 访问者
     */
    abstract public void accept(Visitor visitor);
}
