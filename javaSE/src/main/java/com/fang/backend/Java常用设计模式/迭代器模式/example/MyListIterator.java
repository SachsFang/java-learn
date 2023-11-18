package com.fang.backend.Java常用设计模式.迭代器模式.example;

/**
 * 自定义的List迭代器
 * @author shaobin
 * @date 2022/4/26 16:28
 */
public class MyListIterator extends IIterator {

    private MyList list;

    private int count = 0;

    public MyListIterator(MyList list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return count < list.size();
    }

    @Override
    public Object next() {
        count ++;
        if (count > 0 && count <= list.size()) {
            return list.get(count - 1);
        }
        return null;
    }
}
