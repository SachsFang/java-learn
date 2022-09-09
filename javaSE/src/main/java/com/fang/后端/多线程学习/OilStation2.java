package com.fang.后端.多线程学习;

/**
 * Created by SachsFang on 2021/7/6 10:32
 * 多线程接口-目标对象和弱耦合写法
 */
public class OilStation2 implements Runnable {

    float oil;
    Thread bx1, bx3;

    OilStation2() {
        oil = 0;
        bx1=new Thread(this);
        bx3=new Thread(this);
    }

    public void setOil(float oil) {
        this.oil = oil;
    }

    @Override
    public void run() {
        while (true) {
            float carOil = 0;
            if (bx1 == Thread.currentThread()) {
                carOil = 35;
                oil-=carOil;
                System.out.println("bx1" + "加油了");
            } else if (bx3 == Thread.currentThread()) {
                carOil = 60;
                oil-=carOil;
                System.out.println("bx3" + "加油了");
            }
            System.out.println("油站还有" + oil + "L油)");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (oil <= 0) {
                return;
            }
        }
    }
}
