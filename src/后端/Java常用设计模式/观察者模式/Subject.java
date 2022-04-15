package 后端.Java常用设计模式.观察者模式;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 主题/通知者
 * @author shaobin
 * @Date 2021/7/16 11:57
 */
abstract class Subject {//被观察者

    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
