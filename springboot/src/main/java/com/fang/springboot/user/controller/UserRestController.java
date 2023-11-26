package com.fang.springboot.user.controller;

import com.fang.springboot.common.builder.BaseRespBuilder;
import com.fang.springboot.common.pojo.BaseResp;
import com.fang.springboot.user.pojo.UserPO;
import com.fang.springboot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shaobin
 * @date 2023/11/24 11:07
 */
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/query")
    public BaseResp<List<UserPO>> myBatisPlusQuery(String id) {
        return BaseRespBuilder.success().setData(userService.myBatisQueryUserList(id)).build();
    }

}
