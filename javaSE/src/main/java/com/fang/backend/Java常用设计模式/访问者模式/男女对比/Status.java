package com.fang.backend.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:27
 */
public abstract class Status {
    abstract public void getManAction(Man man);

    abstract public void getWomenAction(Women women);
}
