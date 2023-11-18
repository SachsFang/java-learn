package com.fang.backend.Java常用设计模式.迭代器模式.example;

/**
 * @author shaobin
 * @date 2022/4/26 17:03
 */
public class Client {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.add("fang");
        list.add("100");
        list.add("200");
        list.add("300");
        list.add("ok");
        IIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
