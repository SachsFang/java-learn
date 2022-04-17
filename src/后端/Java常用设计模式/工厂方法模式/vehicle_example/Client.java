package 后端.Java常用设计模式.工厂方法模式.vehicle_example;

import 后端.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * @author shaobin
 * @date 2022/4/4 19:26
 */
public class Client {
    public static void main(String[] args) {
        BwnCarFactory bwnCarFactory = new BwnCarFactory();
        ICar bwnCar = bwnCarFactory.createCar();
        System.out.println(bwnCar.makeInfo());

        BenzCarFactory benzCarFactory = new BenzCarFactory();
        ICar benzCar = benzCarFactory.createCar();
        System.out.println(benzCar.makeInfo());
    }
}
