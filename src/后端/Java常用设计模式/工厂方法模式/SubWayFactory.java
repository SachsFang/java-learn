package 后端.Java常用设计模式.工厂方法模式;

import 后端.Java常用设计模式.简单工厂模式.SubWay;
import 后端.Java常用设计模式.简单工厂模式.Vehicle;

/**
 * @author shaobin
 * @date 2022/4/4 19:12
 */
public class SubWayFactory implements VehicleCreator {

    @Override
    public Vehicle createVehicle() {
        return new SubWay();
    }
}
