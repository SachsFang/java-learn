package 后端.Java常用设计模式.工厂方法模式.vehicle_example;

import 后端.Java常用设计模式.简单工厂模式.vehicle_example.BwnCar;
import 后端.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * @author shaobin
 * @date 2022/4/16 13:11
 */
public class BwnCarFactory implements CarCreator {
    @Override
    public ICar createCar() {
        return new BwnCar();
    }
}
