package com.fang.backend.Java常用设计模式.策略模式and简单工厂.普通用法;


import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 12:00
 * 打折活动-策略模式上下文
 */
public class ChargeContext {
    ChargeStrategy chargeStrategy;

    /**
     * 简单工厂模式结合-构造方法
     * ps:可以用反射优化简单工厂的switch语句
     * @param type
     */
    public ChargeContext(String type) {
        switch (type) {
            case "rebate":
                chargeStrategy = new ChargeRebateStrategy(BigDecimal.valueOf(0.8)); break;
            case "return":
                chargeStrategy = new ChargeReturnStrategy(BigDecimal.valueOf(300), BigDecimal.valueOf(100)); break;
            default:
                chargeStrategy = new ChargeNormalStrategy();
        }
    }

    /**
     * 策略模式-构造方法
     * @param chargeStrategy
     */
    public ChargeContext(ChargeStrategy chargeStrategy) {
        this.chargeStrategy = chargeStrategy;
    }

    public BigDecimal getResult(BigDecimal money) {
        return chargeStrategy.calcResult(money);
    }
}

