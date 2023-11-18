package com.fang.backend.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:31
 */
public class Success extends Status {

    private String status = "成功";

    @Override
    public void getManAction(Man man) {
        System.out.println(man.getName() + status + "，背后有一个伟大的女人");
    }

    @Override
    public void getWomenAction(Women women) {
        System.out.println(women.getName() + status + "，背后有一个不成功的男人");
    }
}
