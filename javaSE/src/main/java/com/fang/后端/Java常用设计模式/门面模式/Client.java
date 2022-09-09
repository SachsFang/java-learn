package com.fang.后端.Java常用设计模式.门面模式;

/**
 * @author shaobin
 * @date 2022/4/14 16:19
 */
public class Client {
    public static void main(String[] args) {
        // 调用子系统方法只需要调用Facade类的方法即可，无需关心子系统
        Facade facade = new Facade();
        facade.facadeMethodOne();
        facade.facadeMethodTwo();
    }
}
