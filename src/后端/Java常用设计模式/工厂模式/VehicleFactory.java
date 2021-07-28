package 后端.Java常用设计模式.工厂模式;

import 后端.Java常用设计模式.抽象工厂模式.AbstractFactory;

/**
 * Created by SachsFang on 2021/7/14 20:51
 */
public class VehicleFactory {
    public Vehicle getVehicle(String type) {
        if (type == null) {
            return null;
        }
        if ("bus".equalsIgnoreCase(type)) {
            return new Bus();
        } else if ("subway".equalsIgnoreCase(type)) {
            return new SubWay();
        } else if ("car".equalsIgnoreCase(type)) {
            return new Car();
        }
        return null;
    }
}
