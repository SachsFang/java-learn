package com.fang.backend.集合.map.hashMap.Jdk7环形链表;

import java.util.HashMap;

/**
 * 测试 JDK1.7 HashMap 环形链表
 *
 * @author shaobin
 * @date 2023/4/25 17:50
 */
public class HashMapInfiniteLoop {

    public static void main(String[] args) {
        // 通过设置断点让线程1和线程2同时debug到transfer方法的首行。注意此时两个线程已经成功添加数据。
        // 放开thread1的断点至transfer方法的“Entry next = e.next;” 这一行；然后放开线程2的的断点，让线程2进行resize

        //遗留问题：三个元素 3 7 11形成的链表是 3 7 还是 3 7 11
        HashMap<Integer, String> map = new HashMap<Integer, String>(2);
        map.put(3, "A");

        new Thread("Thread1") {
            public void run() {
                map.put(5, "B");
                System.out.println(map);
            }

            ;
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put(7, "C");
                System.out.println(map);
            }
        }.start();
    }
}
