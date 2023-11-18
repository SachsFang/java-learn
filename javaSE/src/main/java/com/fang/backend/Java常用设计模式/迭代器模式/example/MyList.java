package com.fang.backend.Java常用设计模式.迭代器模式.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的List
 * @author shaobin
 * @date 2022/4/26 16:38
 */
public class MyList extends IList {

    private List<Object> list = new ArrayList();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Object get(int i) {
        return list.get(i);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public IIterator iterator() {
        return new MyListIterator(this);
    }
}
