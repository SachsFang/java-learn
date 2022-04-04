package 后端.Java常用设计模式.工厂方法模式;

import 后端.Java常用设计模式.简单工厂模式.Vehicle;

/**
 * @author shaobin
 * @date 2022/4/4 19:26
 */
public class Main {
    public static void main(String[] args) {
        BusFactory busFactory = new BusFactory();
        Vehicle bus = busFactory.createVehicle();
        System.out.println(bus.getVehicleSay());

        SubWayFactory subWayFactory = new SubWayFactory();
        Vehicle subWay = subWayFactory.createVehicle();
        System.out.println(subWay.getVehicleSay());

        CarFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        System.out.println(car.getVehicleSay());
    }
}
