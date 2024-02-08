package com.fang.backend.集合.map.hashMap.JDK8读源码;

import java.util.HashMap;

/**
 * @author shaobin
 * @date 2024/2/5 18:34
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);
        map.put(7, "B");

        new Thread("thread1") {// 断点达到thread1线程可以直接看了
            public void run() {
                map.put(9, "C");
            }
        }.start();
        // 首先put方法会先把元素put到就数组中，然后再进行扩容；JDK1.7是先扩容再put。
        // JDK1.8的resize方法通过 if ((e.hash & oldCap) == 0) 方法判断数组下表是否改变，因为使用的是2次幂的扩展(指长度扩为原来2倍)，所以，元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置。
        // 如果等于0，那么元素put进table的下标不变，把元素插入（尾插）到loHead、loTail的链表
        // 如果不等于0，那么元素put进table的下表改变，把元素插入到hiHead、hiTail的链表
        // 最后把lo链表赋值给table[旧table下标]
        // 最后把ti链表赋值给table[旧table下标+旧table容量]
    }
}
