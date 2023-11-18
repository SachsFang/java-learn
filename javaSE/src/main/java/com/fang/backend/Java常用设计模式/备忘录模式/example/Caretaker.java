package com.fang.backend.Java常用设计模式.备忘录模式.example;

/**
 * @author shaobin
 * @date 2022/4/19 18:16
 */
public class Caretaker {

    private Memento memento;

    // 备忘录
    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
