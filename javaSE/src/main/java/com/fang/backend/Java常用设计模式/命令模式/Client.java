package com.fang.backend.Java常用设计模式.命令模式;

/**
 * 奶茶店命令模式
 * @author shaobin
 * @date 2022/5/20 18:44
 */
public class Client {
    public static void main(String[] args) {
        // 奶茶店菜单命令准备
        LemonTeaMaker lemonTeaMaker = new LemonTeaMaker();
        FruitTeaMaker fruitTeaMaker = new FruitTeaMaker();
        Command fruitCommand = new FruitCommand();
        fruitCommand.setTeaMaker(fruitTeaMaker);
        Command lemonCommand = new LemonCommand();
        lemonCommand.setTeaMaker(lemonTeaMaker);
        Waiter girlWaiter = new Waiter();
        /* 顾客点单 */
        // first customer
        girlWaiter.addOrder(fruitCommand);
        girlWaiter.addOrder(lemonCommand);
        girlWaiter.placeOrder();
        // second customer
        girlWaiter.addOrder(fruitCommand);
        girlWaiter.addOrder(lemonCommand);
        girlWaiter.cancelOrder(lemonCommand);
        girlWaiter.placeOrder();
    }
}
