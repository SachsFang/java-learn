package 后端.Java常用设计模式.单例模式;

/**
 * Created by SachsFang on 2021/7/14 20:21
 * 懒汉式单例
 */
public class Bus {

    private static volatile Bus bus = null;// 解决对象半初始化的问题

    private Bus() {}

    public static Bus getInstance() { //线程不安全
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

    public static Bus currentGetInstance() { //线程安全，也叫双重检测锁
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
