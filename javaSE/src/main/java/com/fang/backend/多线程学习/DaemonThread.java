package com.fang.backend.多线程学习;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SachsFang on 2021/7/6 17:32
 */
public class DaemonThread implements Runnable {

    Thread a,b;

    DaemonThread() {
        a = new Thread(this);
        b = new Thread(this);
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        if (thread == a) {
            for (int i = 0; i <= 10; i++) {
                System.out.println("非守护线程输出:"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 10) {
                    System.out.println("非守护线程结束");
                }
            }
        } else if (thread == b) {
            while (true) {
                SimpleDateFormat time = new SimpleDateFormat("mm:ss");
                Date date = new Date();
                System.out.println("守护线程输出:" + time.format(date));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.b.setDaemon(true);
        daemonThread.a.start();
        daemonThread.b.start();
    }
}
