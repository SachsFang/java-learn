package 后端.Java常用设计模式.中介者模式;

/**
 * @author shaobin
 * @date 2022/6/10 17:52
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        Sachs sachs = new Sachs();
        John john = new John();
        // 让中介者认识同事类
        mediator.setJohn(john);
        mediator.setSachs(sachs);
        // 让同事类认识中介者
        sachs.setMediator(mediator);
        john.setMediator(mediator);
        // 同事发送消息通过中介传达
        sachs.send("你工作完成了吗");
        john.send("还没，还要加个班");
    }
}
