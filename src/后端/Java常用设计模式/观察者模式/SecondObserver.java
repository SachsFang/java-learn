package 后端.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 14:06
 */
public class SecondObserver extends Observer {

    private String name;

    public SecondObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println("Second observer " + this.name + " already update");
    }
}
