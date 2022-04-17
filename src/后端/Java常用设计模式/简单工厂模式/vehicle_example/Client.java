package 后端.Java常用设计模式.简单工厂模式.vehicle_example;

/**
 * Created by SachsFang on 2021/7/14 20:54
 */
public class Client {
    public static void main(String[] args) {
        CarSimpleFactory carSimpleFactory = new CarSimpleFactory();
        ICar bwnCar = carSimpleFactory.getBrandCar("bwn");
        System.out.println(bwnCar.makeInfo());
        ICar benzCar = carSimpleFactory.getBrandCar("benz");
        System.out.println(benzCar.makeInfo());
    }
}
