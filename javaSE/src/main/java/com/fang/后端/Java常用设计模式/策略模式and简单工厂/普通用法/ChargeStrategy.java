package com.fang.后端.Java常用设计模式.策略模式and简单工厂.普通用法;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 11:38
 * 打折策略抽象类
 */
public abstract class ChargeStrategy {

    /**
     * 计算打折收费结果
     * @param money
     * @return
     */
    public abstract BigDecimal calcResult(BigDecimal money);
}
