package 后端.Java常用设计模式.中介者模式;

/**
 * @author shaobin
 * @date 2022/6/10 17:48
 */
public class John extends Colleague {

    public void send(String message) {
        message = "John发送了消息-" + message;
        // 这里不用此模式的话就是直接调用John的notify方法了，使用的会就是通过中介者进行交互转发
        this.mediator.send(message, this);
    }

    public void notify(String message) {
        System.out.println("John收到了消息：" + message);
    }

}
