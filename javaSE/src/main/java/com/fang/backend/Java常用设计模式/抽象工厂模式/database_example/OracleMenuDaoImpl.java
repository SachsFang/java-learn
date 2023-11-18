package com.fang.backend.Java常用设计模式.抽象工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/17 14:35
 */
public class OracleMenuDaoImpl implements MenuDao {
    @Override
    public void updateMenu() {
        System.out.println("oracle upadate menu");
    }

    @Override
    public void getMenu() {
        System.out.println("oracle get menu");
    }
}
