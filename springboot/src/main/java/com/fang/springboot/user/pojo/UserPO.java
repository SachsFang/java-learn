package com.fang.springboot.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fang.springboot.user.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaobin
 * @date 2023/11/26 11:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "users")
public class UserPO {

    @TableId(value = "id", type = IdType.AUTO)
    String id;

    private String name;

    @TableField(value = "age")
    private int age;

    private SexEnum sex;

}
