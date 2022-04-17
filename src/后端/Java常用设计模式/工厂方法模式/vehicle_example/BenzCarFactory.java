package 后端.Java常用设计模式.工厂方法模式.vehicle_example;

import 后端.Java常用设计模式.简单工厂模式.vehicle_example.BenzCar;
import 后端.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * @author shaobin
 * @date 2022/4/4 19:13
 */
public class BenzCarFactory implements CarCreator {

    @Override
    public ICar createCar() {
        return new BenzCar();
    }
}
