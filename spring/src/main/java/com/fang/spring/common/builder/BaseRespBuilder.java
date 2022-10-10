package com.fang.spring.common.builder;

import com.fang.spring.common.pojo.BaseResp;

/**
 * @author shaobin
 * @date 2022/9/30 11:43
 */
public final class BaseRespBuilder {

    private BaseResp baseResp;

    private static BaseRespBuilder defaultBuilder() {
        BaseRespBuilder builder = new BaseRespBuilder();
        builder.baseResp = new BaseResp();
        return builder;
    }

    public static BaseRespBuilder success() {
        BaseRespBuilder builder = defaultBuilder();
        builder.baseResp = BaseResp.successResp();
        return builder;
    }

    public static BaseRespBuilder fail() {
        BaseRespBuilder builder = defaultBuilder();
        builder.baseResp = BaseResp.failResp();
        return builder;
    }

    public <T> BaseRespBuilder setData(T data) {
        this.baseResp.setData(data);
        return this;
    }

    public BaseRespBuilder setCode(String code) {
        this.baseResp.setCode(code);
        return this;
    }

    public BaseRespBuilder setMsg(String msg) {
        this.baseResp.setMsg(msg);
        return this;
    }

    public <T> BaseResp<T> build() {
        return this.baseResp;
    }
}
