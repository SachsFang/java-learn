package com.fang.后端.多线程学习.锁.CAS;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
//        SynchronizedObj synchronizedObj = new SynchronizedObj();
        CASObj synchronizedObj = new CASObj();
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10000000; i++) {
                synchronizedObj.increase();
            }
        });
        thread1.start();

        for (int i = 0; i < 10000000; i++) {
            synchronizedObj.increase();
        }
        thread1.join(); // 等运行完 thread1 之后再运行主线程

        long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(synchronizedObj.getNum());
    }
}
