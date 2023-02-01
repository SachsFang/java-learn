package com.fang.micro.impl.user;

import com.fang.micro.api.user.UserService;
import com.fang.micro.api.user.pojo.User;
import com.fang.micro.impl.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebService;

/**
 * @author shaobin
 * @date 2022/12/26 21:35
 */
@Service
@WebService
@RestController //当此项目作为一个服务注册到注册中心时，需要通过RequestMapping访问对应的子服务
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
    public int insertUser(String name, Integer age) {
        return userDAO.insertUser(name, age);
    }
}
