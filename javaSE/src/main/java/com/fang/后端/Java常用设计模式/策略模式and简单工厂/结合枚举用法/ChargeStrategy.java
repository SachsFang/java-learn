package com.fang.后端.Java常用设计模式.策略模式and简单工厂.结合枚举用法;

import java.math.BigDecimal;

public interface ChargeStrategy {

    /**
     * 计算打折收费结果
     * @param money
     * @return
     */
    default BigDecimal calcResult(BigDecimal money) {
        return null;
    }

    default void initData(BigDecimal paramOne, BigDecimal paramTwo){
    };

}
