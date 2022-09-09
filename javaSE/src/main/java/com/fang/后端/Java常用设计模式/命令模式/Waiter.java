package com.fang.后端.Java常用设计模式.命令模式;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/5/23 14:38
 */
public class Waiter {
    List<Command> orderList = new ArrayList<>();

    public void addOrder(Command command) {
        boolean fruitTeaCondition = "FruitCommand".equals(command.getClass().getSimpleName());
        boolean lemonTeaCondition = "LemonCommand".equals(command.getClass().getSimpleName());
        if (fruitTeaCondition ||  lemonTeaCondition) {
            if (fruitTeaCondition && Inventory.FruitAmount > 0) {
                orderList.add(command);
                Inventory.FruitAmount--;
            } else if (lemonTeaCondition && Inventory.LemonAmount > 0) {
                orderList.add(command);
                Inventory.LemonAmount--;
            } else {
                System.out.println("库存不足，请重新点单");
            }
        } else {
            System.out.println("没有这个饮品");
        }
    }

    public void cancelOrder(Command command) {
        orderList.remove(command);
    }

    public void placeOrder() {
        for (Command command : orderList) {
            command.execute();
        }
        orderList.removeAll(orderList);
    }
}
