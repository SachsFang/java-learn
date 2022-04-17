package 后端.Java常用设计模式.简单工厂模式.vehicle_example;

/**
 * @author shaobin
 * @date 2022/4/16 9:15
 */
public class BwnCar implements ICar {
    @Override
    public String makeInfo() {
        return "宝马制造的" + carName;
    }
}
