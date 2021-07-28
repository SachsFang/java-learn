package 后端.Java常用设计模式.观察者模式;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SachsFang on 2021/7/16 11:57
 */
public class Subject {//被观察者

    private String name;
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
