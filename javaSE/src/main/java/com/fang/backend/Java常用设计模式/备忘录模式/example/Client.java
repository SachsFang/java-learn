package com.fang.backend.Java常用设计模式.备忘录模式.example;

/**
 * @author shaobin
 * @date 2022/4/19 18:16
 */
public class Client {
    public static void main(String[] args) {
        // 创建发起者
        Originator originator = new Originator();
        originator.setState("最初状态");
        System.out.println(originator);
        // 创建备忘录并交给管理者存储
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        // 改变发起者状态
        originator.setState("改变了的状态");
        System.out.println(originator);
        // 恢复备份的状态
        originator.setMemento(caretaker.getMemento());
        System.out.println(originator);
    }
}
