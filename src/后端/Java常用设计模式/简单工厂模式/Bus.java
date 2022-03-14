package 后端.Java常用设计模式.简单工厂模式;

/**
 * Created by SachsFang on 2021/7/14 20:49
 */
public class Bus implements Vehicle {
    @Override
    public String getVehicleSay() {
        return "我是公共汽车";
    }
}
