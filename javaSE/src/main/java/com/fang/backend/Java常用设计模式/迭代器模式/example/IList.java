package com.fang.backend.Java常用设计模式.迭代器模式.example;

/**
 * 自定义的抽象List
 * @author shaobin
 * @date 2022/4/26 16:36
 */
public abstract class IList {

    public abstract void add(Object obj);

    public abstract void remove(Object obj);

    public abstract Object get(int i);

    public abstract int size();

    public abstract IIterator iterator();
}
