package 后端.Java常用设计模式.抽象工厂模式;

import 后端.Java常用设计模式.简单工厂模式.Vehicle;

/**
 * Created by SachsFang on 2021/7/15 20:09
 * 参考: https://blog.csdn.net/zyhlwzy/article/details/80707488
 */
public abstract class AbstractFactory { //修改此等级不符合开闭原则,所以在系统详细设计时应该考虑周全
    public abstract Vehicle getBus();
    public abstract Vehicle getCar();
    public abstract Vehicle getSubway();
}
