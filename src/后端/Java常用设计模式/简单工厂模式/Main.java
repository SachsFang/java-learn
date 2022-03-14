package 后端.Java常用设计模式.简单工厂模式;

/**
 * Created by SachsFang on 2021/7/14 20:54
 */
public class Main {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.getVehicle("car");
        System.out.println(vehicle.getVehicleSay());
    }
}
