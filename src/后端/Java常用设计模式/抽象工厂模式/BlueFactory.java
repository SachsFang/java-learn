package 后端.Java常用设计模式.抽象工厂模式;

import 后端.Java常用设计模式.工厂模式.SubWay;
import 后端.Java常用设计模式.工厂模式.Vehicle;

/**
 * Created by SachsFang on 2021/7/16 11:17
 */
public class BlueFactory extends AbstractFactory {
    @Override
    public Vehicle getBus() {
        return new BlueBus();
    }

    @Override
    public Vehicle getCar() {
        return new BlueCar();
    }

    @Override
    public Vehicle getSubway() {
        return new BlueSubway();
    }
}
