package 后端.Java常用设计模式.抽象工厂模式;

import 后端.Java常用设计模式.工厂模式.Vehicle;
import 后端.接口的学习.defaultInterface;

/**
 * Created by SachsFang on 2021/7/16 11:05
 */
public class RedBus implements Vehicle {

    @Override
    public String getVehicleSay() {
        return "I am red bus";
    }
}
