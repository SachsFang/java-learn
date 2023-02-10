package com.fang.micro.api.user.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shaobin
 * @date 2022/12/26 21:30
 */
@Data
public class User implements Serializable {

    private String id;

    private String name;

    private String port;
}
