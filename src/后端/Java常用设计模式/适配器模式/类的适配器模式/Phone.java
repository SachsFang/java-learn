package 后端.Java常用设计模式.适配器模式.类的适配器模式;

/**
 * Created by SachsFang on 2021/7/15 17:30
 */
public class Phone implements PowerPort5v {

    @Override
    public String usePower220v() {
        Adapter adapter = new Adapter();
        return "我是手机,要用5v电压-" + adapter.usePower220v();
    }
}
