package com.fang.backend.多线程学习.锁.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author shaobin
 * @date 2024/4/8 16:34
 */
public class SemaphoreMain {

    // 创建一个计数阈值为 5 的信号量对象
    // 只能 5 个线程同时访问
    static final Semaphore semp = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                doAction();
                System.out.println("信号量队列长度：" + semp.getQueueLength());
            }).start();
        }
    }

    private static void doAction() {
        try { // 申请许可
            semp.acquire();
            try {
                // 业务逻辑
                System.out.println(Thread.currentThread().getName() + ":执行业务逻辑");
                Thread.sleep(3000); // sleep释放cpu资源，但不释放锁
            } catch (Exception e) {
                System.out.println("error");
            } finally {
                // 释放许可
                semp.release();
            }
        } catch (InterruptedException e) {
        }
    }
}
