package com.fang.后端.Java常用设计模式.组合模式.example;

/**
 * 组合抽象类
 * @author shaobin
 * @date 2022/4/21 16:13
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    /**
     * 添加组合
     */
    abstract public void add(Component component);

    /**
     * 移除组合
     */
    abstract public void remove(Component component);

    /**
     * 展示
     */
    abstract public void display(int depth);
}
