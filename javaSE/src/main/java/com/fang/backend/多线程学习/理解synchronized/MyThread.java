package com.fang.backend.多线程学习.理解synchronized;

/**
 * Created by SachsFang on 2021/7/14 14:34
 *
 * https://www.cnblogs.com/shoshana-kong/p/10551952.html
 */
public class MyThread implements Runnable {

    static int oil = 100;

    @Override
    public void run() {
        addOil();
    }

    private void addOil() {//汽车加油,假设油枪只有一个,static保证线程调用的是同一个方法,

            if (oil > 0) {
                synchronized (new Thread()) {
                try {
                    for (int i = 0; i < 5; i++) {
                        oil -= 5;
                        System.out.println("线程ID:" + Thread.currentThread().getId() + "正在加油的第" + (i + 1) + "秒,油站还有 " + oil + "L油");
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
            } else {
                System.out.println("线程ID:" + Thread.currentThread().getId() +"不符合");
            }
    }

    private void doSomething(){
        synchronized (MyThread.class) {
            try {
                Thread.sleep(200);
                for (int i = 0; i < 5; i++) {
                    System.out.print("线程ID:"+Thread.currentThread().getId()+"打印"+i +"\n");
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
