package com.fang.后端.lambdaAND函数式接口;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by SachsFang on 2021/5/8 20:03
 */
public class BaseMethod {
    /*函数式定义方法*/
    @FunctionalInterface
    public interface GreetingService {
        void sayMessage(String message);
    }

    public static void main(String[] args) {
        /*lambda方式的方法实现*/
        GreetingService greetService = message -> System.out.println("Hello " + message);
        greetService.sayMessage("我懂了");

        GreetingService otherGreetService = params -> System.out.println("hello world" + params);
        otherGreetService.sayMessage("哈哈");

        // 更精简的写法
        ((GreetingService) (params -> System.out.println("hello" + params))).sayMessage("精简");

        /*方法引用的方式实现*/
        GreetingService quote = BaseMethod::getMessage;
        quote.sayMessage("引用的方式");

        // 例子
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(10);
        treeSet.add(5);
        treeSet.add(4);
        treeSet.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                System.out.println(value);
            }
        });
        treeSet.forEach(value -> System.out.println(value));
        // 例子
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是新建的线程");
            }
        });
        new Thread(() -> System.out.println("我是新建的线程"));

        /* stream 流 */
        // 串行流 单线程
        treeSet.stream().collect(Collectors.toList());
        // 并行流 多线程
        treeSet.parallelStream().collect(Collectors.toList());
    }

    public static void getMessage(String str) {
        System.out.println(str + ":我后面通过方法引用的方式给GreetingService实现默认抽象方法");
    }
}
