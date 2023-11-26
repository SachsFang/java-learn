package com.fang.springboot.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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

    String id;

    private String name;

    private int age;

}
