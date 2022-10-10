package com.fang.springboot.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author shaobin
 * @date 2022/10/10 10:37
 */
@Data
@AllArgsConstructor
public class UserEvent {

    private String name;

    private String msg;

}
