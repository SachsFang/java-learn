package 后端.Java常用设计模式.工厂方法模式;

import 后端.Java常用设计模式.简单工厂模式.Bus;
import 后端.Java常用设计模式.简单工厂模式.Vehicle;

/**
 * @author shaobin
 * @date 2022/4/4 19:13
 */
public class BusFactory implements VehicleCreator {

    @Override
    public Vehicle createVehicle() {
        return new Bus();
    }
}
