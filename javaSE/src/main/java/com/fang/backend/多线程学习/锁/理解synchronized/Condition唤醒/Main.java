package com.fang.backend.多线程学习.锁.理解synchronized.Condition唤醒;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shaobin
 * @date 2024/4/8 14:52
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        new Thread(() -> {
            main.await();
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                main.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private final Lock lock = new ReentrantLock();
    //Lock lock=new ReentrantLock(true);//公平锁
    //Lock lock=new ReentrantLock(false);//非公平锁
    private final Condition condition = lock.newCondition();//创建 Condition

    public void await() {
        try {
            lock.lock();
            //通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
            System.out.println("开始 wait");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("wait unlock");
        }
    }

    private void signal() {
        try {
            lock.lock();
            Thread.sleep(1000);
            //通过创建 Condition 对象来使线程 signal，必须先执行 lock.lock 方法先获得锁
            condition.signal();//condition 对象的 signal 方法可以唤醒 wait 线程
            System.out.println("唤醒");
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("signal unlock");
        }

    }
}
