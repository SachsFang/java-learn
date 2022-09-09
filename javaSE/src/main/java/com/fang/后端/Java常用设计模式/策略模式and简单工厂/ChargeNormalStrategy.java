package com.fang.后端.Java常用设计模式.策略模式and简单工厂;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 11:59
 */
public class ChargeNormalStrategy extends ChargeStrategy {

    @Override
    BigDecimal calcResult(BigDecimal money) {
        return money;
    }
}
