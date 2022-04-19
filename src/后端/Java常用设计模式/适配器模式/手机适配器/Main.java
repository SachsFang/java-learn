package 后端.Java常用设计模式.适配器模式.手机适配器;

/**
 * Created by SachsFang on 2021/7/15 17:50
 * 重点是要复用系统中原有的 Plug.class
 */
public class Main {
    public static void main(String[] args) {
        Phone phone;
        // 没使用适配器手机只能使用5v电压，输出使用5v电压信息
        phone = new Phone();
        phone.usePower5v();
        // 使用了适配器手机能使用220v电压，输出使用220v电压信息
        phone = new Adapter();
        phone.usePower5v();
    }
}
