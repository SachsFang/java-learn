package 后端.Java常用设计模式.访问者模式.example;

/**
 * @author shaobin
 * @date 2022/6/30 17:52
 */
public class ConcreteElementB extends Element {

    private String elemName = "我是元素B";

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElemB(this);
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }
}
