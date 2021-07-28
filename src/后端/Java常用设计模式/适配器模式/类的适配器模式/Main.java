package 后端.Java常用设计模式.适配器模式.类的适配器模式;

/**
 * Created by SachsFang on 2021/7/15 17:50
 * 重点是要复用系统中原有的 Plug.class
 */
public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();
        System.out.println(phone.usePower220v());
    }
}
