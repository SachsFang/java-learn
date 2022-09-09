package com.fang.后端.多线程学习;

/**
 * Created by SachsFang on 2021/7/6 10:59
 * 线程同步-synchronized关键字
 */
public class OilStation3 implements Runnable {

    Thread oilTank;
    Thread bx1;
    float oil;

    OilStation3() {
        oil = 0;
        oilTank = new Thread(this);
        bx1 = new Thread(this);
        oilTank.setName("油车");
        bx1.setName("宝马x1");
    }

    @Override
    public void run() {
        if (oilTank == Thread.currentThread()) {
            addOrTakeOil("add");
        } else {
            addOrTakeOil("take");
        }
    }

    public synchronized void addOrTakeOil(String type) {
        if ("add".equals(type)) {
            for (int i = 0; i < 5; i++) {
                oil+=30;
                System.out.println("油车正在加油，每次加30L");
                try {
                    oilTank.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("油车加油完毕，油站有" + oil + "L油");
        } else {
            while (oil>55) {
                oil-=55;
                System.out.println("bx1正在加油，每次加55L，油站还剩"+oil+"L油");
                try {
                    bx1.sleep(3000);
                    System.out.println("bx1跑了N圈，油用完了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
