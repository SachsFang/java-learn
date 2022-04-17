package 后端.Java常用设计模式.简单工厂模式.vehicle_example;

/**
 * @author shaobin
 * @date 2022/4/16 12:14
 */
public class CarSimpleFactory {
    public ICar getBrandCar(String type) {
        if (type == null) {
            return null;
        }
        if ("benz".equalsIgnoreCase(type)) {
            return new BenzCar();
        } else if ("bwn".equalsIgnoreCase(type)) {
            return new BwnCar();
        }
        return null;
    }
}
