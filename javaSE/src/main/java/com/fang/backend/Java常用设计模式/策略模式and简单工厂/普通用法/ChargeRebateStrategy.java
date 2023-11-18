package com.fang.backend.Java常用设计模式.策略模式and简单工厂.普通用法;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 11:56
 */
public class ChargeRebateStrategy extends ChargeStrategy {
    private BigDecimal rebate;
    public ChargeRebateStrategy(BigDecimal rebate) {
        this.rebate = rebate;
    }

    @Override
    public BigDecimal calcResult(BigDecimal money) {
        return money.multiply(rebate);
    }
}
