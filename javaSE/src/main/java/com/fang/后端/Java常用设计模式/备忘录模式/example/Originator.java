package com.fang.后端.Java常用设计模式.备忘录模式.example;

/**
 * @author shaobin
 * @date 2022/4/19 18:11
 */
public class Originator {

    private String state;

    private Memento memento;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMemento(Memento memento) {
        this.state = memento.getState();
    }

    public Memento createMemento() {
        return new Memento(this.state);
    }

    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                ", memento=" + memento +
                '}';
    }
}
