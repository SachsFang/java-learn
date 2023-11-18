package com.fang.backend.Java常用设计模式.策略模式and简单工厂.结合枚举用法;


import java.math.BigDecimal;
import java.util.Objects;

public enum ChargeStrategyEnum implements ChargeStrategy {

    NORMAL_STRATEGY(1, "普通策略"){
        @Override
        public BigDecimal calcResult(BigDecimal money) {
            return money;
        }
    },

    REBATE_STRATEGY(2, "打折策略"){

        private BigDecimal rebate;

        @Override
        public void initData(BigDecimal paramOne, BigDecimal paramTwo) {
            rebate = paramOne;
        }

        @Override
        public BigDecimal calcResult(BigDecimal money) {
            return BigDecimal.valueOf(0.8).multiply(money);
        }
    },

    RETURN_STRATEGY(3, "满返策略"){

        private BigDecimal moneyCondition;

        private BigDecimal moneyReturn;

        @Override
        public void initData(BigDecimal paramOne, BigDecimal paramTwo) {
            moneyCondition = paramOne;
            moneyReturn = paramTwo;
        }

        @Override
        public BigDecimal calcResult(BigDecimal money) {
            if (Objects.nonNull(money) && money.compareTo(moneyCondition) > -1) {
                return money.subtract(moneyReturn);
            }
            return money;
        }
    },

    OTHER(0, "其他");

    private Integer id;

    private String text;

    ChargeStrategyEnum(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
