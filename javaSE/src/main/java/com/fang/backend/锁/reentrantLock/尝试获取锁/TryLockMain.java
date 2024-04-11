package com.fang.backend.锁.reentrantLock.尝试获取锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shaobin
 * @date 2024/3/28 17:20
 */
public class TryLockMain {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        int count = 0;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //让thread获取到锁
                lock.lock();
                System.out.println("thread1获取到了锁");
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });
        //确保thread已经启动，并且持有锁了
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 尝试获取锁，在指定时间内获取
                    boolean flag = lock.tryLock(1, TimeUnit.SECONDS);
                    if (flag) {
                        System.out.println("thread2获取到锁了");
                    } else  {
                        System.out.println("thread2获取不到锁");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();
    }
}
