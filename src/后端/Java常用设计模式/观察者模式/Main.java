package 后端.Java常用设计模式.观察者模式;

/**
 * Created by SachsFang on 2021/7/16 13:56
 */
public class Main {
    public static void main(String[] args) {
        // 主题/通知者
        ConcreteSubject concreteSubject = new ConcreteSubject("concreteSubject");
        // 观察者1
        FirstObserver firstObserver = new FirstObserver("fo");
        // 观察者2
        SecondObserver secondObserver = new SecondObserver("so");
        // 加入观察者
        concreteSubject.attach(firstObserver);
        concreteSubject.attach(secondObserver);
        // 向观察者们发出通知
        concreteSubject.notifyObserver();
        // 去除观察者1
        concreteSubject.detach(firstObserver);
        // 向观察者们发出通知
        concreteSubject.notifyObserver();
    }
}
