package 后端.Java常用设计模式.抽象工厂模式;

/**
 * Created by SachsFang on 2021/7/15 19:48
 * 调用了工厂模式中的VehicleFactory
 */
public class Main {
    public static void main(String[] args) {
        FactoryProducer factoryProducer = new FactoryProducer();
        AbstractFactory blueFactory = factoryProducer.getFactory("blue");

        System.out.println(blueFactory.getBus().getVehicleSay());
    }
}
