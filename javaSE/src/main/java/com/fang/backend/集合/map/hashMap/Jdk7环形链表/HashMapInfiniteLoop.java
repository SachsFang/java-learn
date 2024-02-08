package com.fang.backend.集合.map.hashMap.Jdk7环形链表;

import java.util.HashMap;

/**
 * 测试 JDK1.7 HashMap 环形链表
 *
 * @author shaobin
 * @date 2023/4/25 17:50
 */
public class HashMapInfiniteLoop {

    /**
     * 运行此个main直接新open一个idea窗口然后配置SDK为jdk1.7即可（不要改本java-learn项目，java-learn项目换成JDK1.7 lamuda写法不支持）
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // 通过设置断点让线程1和线程2同时debug至 transfer方法的“Entry next = e.next;” 这一行
        // 然后放开线程2的的断点，让线程2进行resize
        // 在放开线程1的断点，让线程1resize，然后map就形成环形链表了

        //遗留问题：三个元素 3 7 11形成的链表是 3 7 还是 3 7 11
        final HashMap<Integer, String> map = new HashMap<Integer, String>(2, 1f);
        map.put(7, "B");
        map.put(3, "C");

        new Thread("thread1") {
            public void run() {
                map.put(9, "T1");
                System.out.println(map.get(7));
                System.out.println(map);
            }
        }.start();
        new Thread("thread2") {
            public void run() {
                map.put(11, "T2");
            }
        }.start();
    }


}
