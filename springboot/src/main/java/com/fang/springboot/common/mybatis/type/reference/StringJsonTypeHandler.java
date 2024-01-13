package com.fang.springboot.common.mybatis.type.reference;

import com.alibaba.fastjson.TypeReference;
import com.fang.springboot.common.mybatis.type.ListJsonTypeHandler;

import java.util.List;

public class StringJsonTypeHandler extends ListJsonTypeHandler<String> {
    @Override
    protected TypeReference<List<String>> specificType() {
        return new TypeReference<List<String>>() {
        };
    }
}