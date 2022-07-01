package 后端.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 14:09
 */
public class FallInLove extends Status{

    private String status = "谈恋爱";

    @Override
    public void getManAction(Man man) {
        System.out.println(man.getName() + status + "，遇事不懂也要装懂");
    }

    @Override
    public void getWomenAction(Women women) {
        System.out.println(women.getName() + status + "，遇事懂也要装不懂");
    }
}
