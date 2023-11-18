package com.fang.backend.Java常用设计模式.策略模式and简单工厂;

import com.fang.backend.Java常用设计模式.策略模式and简单工厂.普通用法.ChargeContext;
import com.fang.backend.Java常用设计模式.策略模式and简单工厂.结合枚举用法.ChargeStrategy;
import com.fang.backend.Java常用设计模式.策略模式and简单工厂.结合枚举用法.ChargeStrategyEnum;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 12:39
 * 打折活动客户端
 */
public class Client {
    public static void main(String[] args) {
        /*普通用法*/
        ChargeContext chargeContextNormal = new ChargeContext("normal");
        System.out.println(chargeContextNormal.getResult(BigDecimal.valueOf(600)));

        ChargeContext chargeContextRebate = new ChargeContext("rebate");
        System.out.println(chargeContextRebate.getResult(BigDecimal.valueOf(600)));

        ChargeContext chargeContextReturn = new ChargeContext("return");
        System.out.println(chargeContextReturn.getResult(BigDecimal.valueOf(600)));

        /*结合枚举*/
        // 结合枚举的好处是不需要新增类，只需在枚举新增新的策略即可
        ChargeStrategy normalStrategy = ChargeStrategyEnum.NORMAL_STRATEGY;
        System.out.println(normalStrategy.calcResult(BigDecimal.valueOf(600)));;

        ChargeStrategyEnum rebateStrategy = ChargeStrategyEnum.REBATE_STRATEGY;
        rebateStrategy.initData(BigDecimal.valueOf(0.8), null);
        System.out.println(rebateStrategy.calcResult(BigDecimal.valueOf(600)));

        ChargeStrategyEnum returnStrategy = ChargeStrategyEnum.RETURN_STRATEGY;
        returnStrategy.initData(BigDecimal.valueOf(300), BigDecimal.valueOf(100));
        System.out.println(returnStrategy.calcResult(BigDecimal.valueOf(600)));
    }
}
