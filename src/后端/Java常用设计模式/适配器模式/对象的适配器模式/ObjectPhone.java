package 后端.Java常用设计模式.适配器模式.对象的适配器模式;

import 后端.Java常用设计模式.适配器模式.类的适配器模式.Plug;
import 后端.Java常用设计模式.适配器模式.类的适配器模式.PowerPort5v;

/**
 * Created by SachsFang on 2021/7/15 19:30
 */
public class ObjectPhone implements PowerPort5v {
    @Override
    public String usePower220v() {
        ObjectAdapter objectAdapter = new ObjectAdapter(new Plug());
        return "我是手机,要用5v电压-" + objectAdapter.usePower220v();
    }
}
