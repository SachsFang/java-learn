package 后端.Java常用设计模式.适配器模式.对象的适配器模式;

/**
 * Created by SachsFang on 2021/7/15 19:29
 */
public class Main {
    public static void main(String[] args) {
        ObjectPhone objectPhone = new ObjectPhone();
        System.out.println(objectPhone.usePower220v());
    }
}
