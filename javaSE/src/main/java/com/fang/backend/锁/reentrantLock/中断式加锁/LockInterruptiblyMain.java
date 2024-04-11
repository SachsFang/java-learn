package com.fang.backend.锁.reentrantLock.中断式加锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shaobin
 * @date 2024/3/28 17:15
 */
public class LockInterruptiblyMain {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock(true);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //让thread获取到锁
                lock.lock();
                System.out.println("thread获取到了锁");
                //不提供解锁的操作，一直让thread占有这把锁
            }
        });
        thread.start();
        //确保thread已经启动，并且持有锁了
        Thread.sleep(1000);

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                //不让thread1获取到锁，同时thread1是"可中断式"加锁
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("thread1的阻塞等待被中断了");
                }
            }
        });
        thread1.start();
        //main线程尝试唤醒thread1，thread则会抛出中断异常
        thread1.interrupt();
    }
}
