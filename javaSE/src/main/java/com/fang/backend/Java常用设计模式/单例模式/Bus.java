package com.fang.backend.Java常用设计模式.单例模式;

/**
 * Created by SachsFang on 2021/7/14 20:21
 * 懒汉式单例
 */
public class Bus {

    /**
     * volatile关键字解决对象半初始化的问题
     */
    private static volatile Bus bus = null;

    private Bus() {}

    public static Bus getInstance() { //线程不安全
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

    public static Bus currentGetInstance() { //线程安全，也叫双重检测锁
        // 这个if是为了性能，当创建了对象后，不需要再用到锁了，用锁会影响代码性能，所以用这个if跳过锁代码
        if (bus == null) {
            synchronized (Bus.class) {
                if (bus == null) {
                    bus = new Bus();
                }
            }
        }
        return bus;
    }
}
