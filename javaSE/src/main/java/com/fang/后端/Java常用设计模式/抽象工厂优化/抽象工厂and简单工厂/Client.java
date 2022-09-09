package com.fang.后端.Java常用设计模式.抽象工厂优化.抽象工厂and简单工厂;

/**
 * @author shaobin
 * @date 2022/4/17 15:24
 */
public class Client {
    public static void main(String[] args) {
        /* 在抽象工厂基础上抽出一个DataAccess便可解决多个客户段代码调用时，修改db需要改动多处的问题
        * 但每次增加数据库族时都要改动switch语句，可用放射来优化switch语句*/
        DataAccess.getMenuDao().updateMenu();
        DataAccess.getMenuDao().getMenu();
        DataAccess.getUserDao().updateUser();
        DataAccess.getMenuDao().getMenu();
    }
}
