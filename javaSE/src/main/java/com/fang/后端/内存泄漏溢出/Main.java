package com.fang.后端.内存泄漏溢出;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shaobin
 * @date 2023/3/5 18:36
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        testEqualSignAndEqualMethodDifference();
    }

    private static void testEqualSignAndEqualMethodDifference() throws InterruptedException {
        User user1 = new User();
        user1.setName("11");
        user1.setAge(11);
        User user2 = new User();
        user2.setName("11");
        user2.setAge(11);
        /*注释掉User的hashcode和equal走这段代码*/
        System.out.println("对象不重写equal或hashcode方法：user1 == user2比较内存结果" + (user1 == user2));
        System.out.println("对象不重写equal或hashcode方法：user1和user2会调用Object的==比较内存地址" + user1.equals(user2));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        // Set和Map的key是根据对象的hashcode和equal方法判断是否是相等的对象，如果使用的时候不重写对象的hashcode和equal方法
        // 虽然是相等的对象，但用的是Object的hashcode和equal方法比较，结果肯定不一样，会把相等的对象放入set或map中，可能会造成内存泄漏问题
        Set<Object> set = new HashSet<>(1024);
        set.add(user1);
        set.add(user2);
        System.out.println("对象不重写equal或hashcode方法：" + set);
        // 发生内存泄漏导致内存溢出
        long count = 0;
        while (true) {
            set.add(new User("fang", 22, 100));
            if (count++ % 1024 == 0) {
                System.out.println("set size: " + set.size());
                System.out.println("运行 " + count + "次后，可用内存剩余：" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB");
            }
        }
    }
}
