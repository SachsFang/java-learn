package 后端.Java常用设计模式.适配器模式.类的适配器模式.手机适配器;

/**
 * Created by SachsFang on 2021/7/15 17:38
 */
public class Adapter extends Plug implements PowerPort5v {

    @Override
    public String usePower220v() {
        String origin = this.outPut220v();
        return origin + "-经过了适配器处理-变成5v输出了";
    }
}
