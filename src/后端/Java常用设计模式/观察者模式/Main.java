package 后端.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 13:56
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new FirstObserver(subject);
        new SecondObserver(subject);

        subject.setName("fang");
    }
}
