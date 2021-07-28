package 后端.Java常用设计模式.抽象工厂模式;

import 后端.Java常用设计模式.工厂模式.Vehicle;

/**
 * Created by SachsFang on 2021/7/16 11:15
 */
public class RedFactory extends AbstractFactory {
    @Override
    public Vehicle getBus() {
        return new RedBus();
    }

    @Override
    public Vehicle getCar() {
        return new RedCar();
    }

    @Override
    public Vehicle getSubway() {
        return new RedSubway();
    }
}
