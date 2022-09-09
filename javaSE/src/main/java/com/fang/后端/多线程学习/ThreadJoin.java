package com.fang.后端.多线程学习;

/**
 * Created by SachsFang on 2021/7/6 16:46
 */
public class ThreadJoin implements Runnable {

    Cake cake;
    Thread joinTread;

    class Cake {
        int price;
        String customer;
        Cake(int price, String customer) {
            this.price = price;
            this.customer = customer;
        }
    }

    public void setJoinThread(Thread joinThread) {
        this.joinTread = joinThread;
    }

    @Override
    public void run() {
        String person = Thread.currentThread().getName();
        if (person.equals("customer")) {
            System.out.println("顾客来买蛋糕");
            try {
                joinTread.join();
                System.out.println(cake.customer + "付了" + cake.price + "买到了蛋糕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (person.equals("cakeMaker")) {
            try {
                Thread.sleep(100);//保证customer先走
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("师傅正在做蛋糕，请稍等...");
            cake = new Cake(100, "sachs");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("蛋糕做好了，请付"+cake.price);
        }
    }

    public static void main(String[] args) {
        ThreadJoin threadJoin = new ThreadJoin();
        Thread customer = new Thread(threadJoin);
        Thread cakeMaker = new Thread(threadJoin);
        threadJoin.setJoinThread(cakeMaker);
        customer.setName("customer");
        cakeMaker.setName("cakeMaker");
        customer.start();
        cakeMaker.start();
    }
}
