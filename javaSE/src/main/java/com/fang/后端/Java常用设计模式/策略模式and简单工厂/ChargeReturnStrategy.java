package com.fang.后端.Java常用设计模式.策略模式and简单工厂;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 11:43
 */
public class ChargeReturnStrategy extends ChargeStrategy {
    private BigDecimal moneyCondition;
    private BigDecimal moneyReturn;
    public ChargeReturnStrategy(BigDecimal moneyCondition, BigDecimal moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }
    @Override
    BigDecimal calcResult(BigDecimal money) {
        BigDecimal result = money;
        if (money.compareTo(moneyCondition) > -1) {
            result = money.subtract(moneyReturn);
        }
        return result;
    }
}
