package com.fang.backend.Java常用设计模式.抽象工厂优化.抽象工厂and反射;

/**
 * @author shaobin
 * @date 2022/4/17 16:03
 */
public class Client {
    public static void main(String[] args) {
        DataAccess.getMenuDao().updateMenu();
        DataAccess.getMenuDao().getMenu();
        DataAccess.getUserDao().updateUser();
        DataAccess.getUserDao().getUser();
    }
}
