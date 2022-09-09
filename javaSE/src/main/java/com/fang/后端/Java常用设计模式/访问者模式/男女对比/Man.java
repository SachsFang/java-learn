package com.fang.后端.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:26
 */
public class Man extends Person{

    private String name = "男人";

    @Override
    public void accept(Status status) {
        status.getManAction(this);
    }

    public String getName() {
        return name;
    }
}
