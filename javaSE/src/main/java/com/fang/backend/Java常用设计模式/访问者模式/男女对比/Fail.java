package com.fang.backend.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:36
 */
public class Fail extends Status{

    private String status = "失败";

    @Override
    public void getManAction(Man man) {
        System.out.println(man.getName() + status + "，闷头喝酒，谁也不用劝");
    }

    @Override
    public void getWomenAction(Women women) {
        System.out.println(women.getName() + status + "，眼泪汪汪，谁也劝不动");
    }
}
