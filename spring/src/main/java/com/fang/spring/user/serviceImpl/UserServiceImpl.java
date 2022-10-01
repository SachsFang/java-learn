package com.fang.spring.user.serviceImpl;

import com.fang.spring.user.daoImpl.UserDaoImpl;
import com.fang.spring.user.mapper.UserMapper;
import com.fang.spring.user.pojo.User;
import com.fang.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/9/4 12:17
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl myUserDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser() {
        // 模拟出错
        int i = 1 / 0;
        myUserDao.addUser();
    }

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public User queryById(String id) {
        return userMapper.queryById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(String id) {
        return userMapper.deleteById(id);
    }

    public void setMyUserDao(UserDaoImpl myUserDao) {
        this.myUserDao = myUserDao;
        System.out.println("生命周期：注入IOC容器对象");
    }

    public UserDaoImpl getMyUserDao() {
        System.out.println("赋值当前对象需要通过get方法获取到当前对象");
        return myUserDao;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "myUserDao=" + myUserDao +
                '}';
    }
}
