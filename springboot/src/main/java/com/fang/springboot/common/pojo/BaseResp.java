package com.fang.springboot.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shaobin
 * @date 2022/9/30 11:37
 */
@Data
@NoArgsConstructor
public class BaseResp<T>implements Serializable {

    public static final String SUCCESS_CODE = "200";

    public static final String FAIL_CODE = "100";

    public static final String SUCCESS_MES = "操作成功";

    public static final String FAIL_MES = "操作失败";

    String code;

    String msg;

    T data;

    public BaseResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BaseResp successResp() {
        return new BaseResp<>(SUCCESS_CODE, SUCCESS_MES);
    }

    public static BaseResp failResp() {
        return new BaseResp(FAIL_CODE, FAIL_MES);
    }

}
