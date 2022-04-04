package 后端.Java常用设计模式.工厂方法模式;

import 后端.Java常用设计模式.简单工厂模式.Vehicle;

/**
 * @author shaobin
 * @date 2022/4/4 19:13
 */
public interface VehicleCreator {

    /**
     * 创建交通工具
     * @return
     */
    Vehicle createVehicle();

}
