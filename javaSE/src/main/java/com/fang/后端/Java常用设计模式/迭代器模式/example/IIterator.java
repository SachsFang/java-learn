package com.fang.后端.Java常用设计模式.迭代器模式.example;

/**
 * 自定义的抽象迭代器
 * @author shaobin
 * @date 2022/4/26 16:23
 */
public abstract class IIterator {
    public abstract boolean hasNext();

    public abstract Object next();
}
