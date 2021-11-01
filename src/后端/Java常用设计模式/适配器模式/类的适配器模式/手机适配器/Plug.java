package 后端.Java常用设计模式.适配器模式.类的适配器模式.手机适配器;

/**
 * Created by SachsFang on 2021/7/15 17:40
 * 插头类
 */
public class Plug implements PowerPort220v {
    @Override
    public String outPut220v() {
        return "我是插头,输出220v";
    }
}
