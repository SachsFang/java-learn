package 后端.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 14:06
 */
public class SecondObserver extends Observer {

    public SecondObserver(String name, Subject subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println("Second observer " + super.name + " already update");
    }
}
