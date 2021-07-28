package 后端.Java常用设计模式.适配器模式.对象的适配器模式;

import 后端.Java常用设计模式.适配器模式.类的适配器模式.Plug;
import 后端.Java常用设计模式.适配器模式.类的适配器模式.PowerPort5v;

/**
 * Created by SachsFang on 2021/7/15 17:38
 * 重点是通过Plug plug传入实例参数,通过实参调用系统原对象的方法,遵循开闭原则
 */
public class ObjectAdapter implements PowerPort5v {

    Plug plug;

    ObjectAdapter(Plug plug) {
        this.plug = plug;
    }

    @Override
    public String usePower220v() {
        String origin = plug.outPut220v();
        return origin + "-经过了适配器处理-变成5v输出了";
    }
}
