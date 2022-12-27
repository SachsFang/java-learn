package com.fang.micro.impl.user;

import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/12/26 21:35
 */
@Service
@WebService
public class UserServiceImpl implements UserService {
    @Override
    public User getUserInfo() {
        User user = new User();
        user.setId("01");
        user.setName("fang");
        return user;
    }
}
