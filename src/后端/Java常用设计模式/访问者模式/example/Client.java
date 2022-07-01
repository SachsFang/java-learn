package 后端.Java常用设计模式.访问者模式.example;

/**
 * @author shaobin
 * @date 2022/6/30 18:08
 */
public class Client {
    public static void main(String[] args) {
        // 创建元素结构对象（不变）
        ConcreteElementA concreteElementA = new ConcreteElementA();
        ConcreteElementB concreteElementB = new ConcreteElementB();
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(concreteElementA);
        objectStructure.attach(concreteElementB);
        // 访问者访问（支持开闭原则不断添加）
        ConcreteVisitorA concreteVisitorA = new ConcreteVisitorA();
        objectStructure.accept(concreteVisitorA);

        ConcreteVisitorB concreteVisitorB = new ConcreteVisitorB();
        objectStructure.accept(concreteVisitorB);
    }
}
