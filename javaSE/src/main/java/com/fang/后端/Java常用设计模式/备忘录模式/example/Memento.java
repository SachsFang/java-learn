package com.fang.后端.Java常用设计模式.备忘录模式.example;

/**
 * @author shaobin
 * @date 2022/4/19 18:12
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
