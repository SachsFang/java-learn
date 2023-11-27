package com.fang.springboot.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author shaobin
 * @date 2023/11/27 9:47
 */
@Getter
public enum SexEnum {

    UNKNOWN(0, "未知"),

    MAN(1, "男"),

    WOMEN(2, "女");

    @EnumValue  //在需要保存到数据库的值上面加上注解
    private Integer id;

    @JsonValue    //需要在前端展示哪个值就在哪个属性上加上该注
    private String text;

    SexEnum(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
