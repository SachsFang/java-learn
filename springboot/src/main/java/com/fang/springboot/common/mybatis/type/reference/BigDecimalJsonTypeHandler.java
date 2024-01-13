package com.fang.springboot.common.mybatis.type.reference;

import com.alibaba.fastjson.TypeReference;
import com.fang.springboot.common.mybatis.type.ListJsonTypeHandler;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalJsonTypeHandler extends ListJsonTypeHandler<BigDecimal> {

    @Override
    protected TypeReference<List<BigDecimal>> specificType() {
        return new TypeReference<List<BigDecimal>>() {
        };
    }
}
