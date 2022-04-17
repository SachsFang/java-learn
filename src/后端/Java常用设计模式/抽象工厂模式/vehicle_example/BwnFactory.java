package 后端.Java常用设计模式.抽象工厂模式.vehicle_example;

import 后端.Java常用设计模式.简单工厂模式.vehicle_example.BwnCar;
import 后端.Java常用设计模式.简单工厂模式.vehicle_example.ICar;

/**
 * 宝马交通工具工厂 - 宝马品牌产品族
 * @author shaobin
 * @date 2022/4/4 19:13
 */
public class BwnFactory implements BrandAbstractFactory {

    @Override
    public IBus createBus() {
        return new BwnBus();
    }

    @Override
    public ICar createCar() {
        return new BwnCar();
    }
}
