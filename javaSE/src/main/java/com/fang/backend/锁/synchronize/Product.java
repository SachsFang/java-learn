package com.fang.backend.锁.synchronize;

/**
 * @author shaobin
 * @date 2024/3/27 15:38
 */
public class Product {
    public synchronized void synchronizedMethodA() {
        try {
            // 这是一个同步方法，需要获取对象的锁
            System.out.println(Thread.currentThread().getName() + "获取方法A锁了");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "释放方法A锁了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void synchronizedMethodB() {
        try {
            // 这是一个同步方法，需要获取对象的锁
            System.out.println(Thread.currentThread().getName() + "获取方法B锁了");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "释放方法B锁了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void nonSynchronizedMethod() {
        // 这是一个非同步方法，不需要获取对象的锁
        System.out.println("无不需要获取对象锁就可以输出");
    }

    public static void main(String[] args) {
        // 如果一个对象有非synchronized的方法，那么这些方法的访问不会受到锁的影响。即使一个线程正在执行该对象的某个synchronized方法，其他线程仍然可以自由地调用该对象的非synchronized方法
        // 如果使用synchronized关键字来锁定一个对象的方法时，只有那些被明确标记为synchronized的方法会受到锁的影响，并且当一个线程正在执行某个对象的synchronized方法时，其他线程不能进入该对象的任何其他synchronized方法，因为它们都需要获取相同的锁，所以也称做对象锁
        Product product = new Product();
        Thread thread1 = new Thread(() -> {
            product.synchronizedMethodA();
        });
        Thread thread2 = new Thread(() -> {
            product.nonSynchronizedMethod();
        });
        Thread thread3 = new Thread(() -> {
            product.synchronizedMethodB();
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}