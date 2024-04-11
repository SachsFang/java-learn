package com.fang.backend.多线程学习.锁.理解synchronized.示例;

/**
 * Created by SachsFang on 2021/7/14 14:33
 */
public class Synchronized {
    public static void main(String[] args) {
        /*Thread thread1 = new Thread(new MyThread());
        Thread thread2 = new Thread(new MyThread());
        Thread thread3 = new Thread(new MyThread());
        thread1.start();
        thread2.start();
        thread3.start();*/
        MyThread myThread = new MyThread();
        Thread thread4 = new Thread(myThread);
        Thread thread5 = new Thread(myThread);
        Thread thread6 = new Thread(myThread);
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
