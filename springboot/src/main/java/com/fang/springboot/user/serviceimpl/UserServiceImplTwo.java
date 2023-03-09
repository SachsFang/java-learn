package com.fang.springboot.user.serviceimpl;

import com.fang.springboot.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author shaobin
 * @date 2023/3/9 17:13
 */
@Service("userService2")
public class UserServiceImplTwo implements UserService {
    @Override
    public String printInfo() {
        return null;
    }

    @Override
    public int insertUser(String name, Integer age) {
        return 0;
    }

    @Override
    public String testSpringAnnotationTransactional() {
        return null;
    }

    @Override
    public String testManualTransactional() {
        return null;
    }

    @Override
    public String testMyTransactional() {
        return null;
    }
}
