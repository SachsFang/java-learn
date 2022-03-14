package 后端.Java常用设计模式.抽象工厂模式;

/**
 * Created by SachsFang on 2021/7/15 20:08
 */
public class FactoryProducer {
    AbstractFactory factory;

    public AbstractFactory getFactory(String type) {
        if (type == null) {
            return null;
        }
        if ("red".equalsIgnoreCase(type)) {
            return new RedFactory();
        } else if ("blue".equals(type)) {
            return new BlueFactory();
        }
        return null;
    }
}
