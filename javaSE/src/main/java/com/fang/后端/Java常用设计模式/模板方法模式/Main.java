package com.fang.后端.Java常用设计模式.模板方法模式;

/**
 * @author shaobin
 * @date 2022/4/13 18:35
 */
public class Main {
    public static void main(String[] args) {
        System.out.print("Student A answer:");
        new Student("A", "B", "C");
        System.out.print("Student B answer:");
        Student studentB = new Student("C", "B", "D");
    }
}
