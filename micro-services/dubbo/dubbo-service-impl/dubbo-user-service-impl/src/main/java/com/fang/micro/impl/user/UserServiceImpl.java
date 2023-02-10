package com.fang.micro.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import com.fang.micro.impl.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.jws.WebService;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/12/26 21:35
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${server.port}")
    private String port;

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserInfo() {
        User user = new User();
        user.setId("01");
        user.setName("fang");
        user.setPort(port);
        return user;
    }

    @Override
    public List<User> getUserInfoList() {
        return userDAO.getUserList();
    }

    @Override
    public int insertUser(String name, Integer age) {
        return userDAO.insertUser(name, age);
    }
}
