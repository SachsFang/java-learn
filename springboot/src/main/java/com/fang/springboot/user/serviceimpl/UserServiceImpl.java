package com.fang.springboot.user.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.springboot.common.annotation.MyTransactional;
import com.fang.springboot.common.util.TransactionalUtils;
import com.fang.springboot.user.dao.UserDAO;
import com.fang.springboot.user.dao.UserMapper;
import com.fang.springboot.user.enums.SexEnum;
import com.fang.springboot.user.pojo.UserPO;
import com.fang.springboot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Objects;

/**
 * @author shaobin
 * @date 2022/8/5 20:06
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    TransactionalUtils transactionalUtils;

    @Override
    public String printInfo() {
        System.out.println("call this service success");
        return "server execute success";
    }

    @Override
    public int jpaInsertUser(String name, Integer age) {
        return userDAO.insertUser(name, age, 0);
    }

    @Override
    public int jpaInsertUser(String name, Integer age, Integer sex) {
        return userDAO.insertUser(name, age, sex);
    }

    @Override
    public int myBatisInsertUser(String name, Integer age, SexEnum sex) {
//        UserPO userPO = new UserPO(null, name, age, sex);
        int result = 0;
//        result = userMapper.insert(userPO);
        result = userMapper.insertUser(name, age, sex);
        return result;
    }

    @Override
    public List<UserPO> myBatisQueryUserList(SexEnum sex) {
        List<UserPO> userPOS = userMapper.selectList(
                new QueryWrapper<UserPO>().lambda()
                        .eq(Objects.nonNull(sex), UserPO::getSex, sex)
        );
        return userPOS;
    }



    @Override
    @Transactional
    public String testSpringAnnotationTransactional() {
        try {
            jpaInsertUser("transactional", 22);
            int i = 1 / 0;
            return "ok";
        } catch (Exception e) {// 这里被捕获了，事务失效
            // 失效的情况下需要手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "fail";
        }
//        insertUser("transactional", 22);
//        int i = 1 / 0;
//        return "ok";
    }

    @Override
    public String testManualTransactional() {
        TransactionStatus begin = null;
        try {
            begin = transactionalUtils.begin();
            jpaInsertUser("transactional", 24);
            int i = 1 / 0;
            transactionalUtils.commit(begin);
            return "ok";
        } catch (Exception e) {// 这里被捕获了，事务失效
            // 失效的情况下手动回滚
            transactionalUtils.rollback(begin);
            return "fail";
        }
    }

    @Override
    @MyTransactional
    public String testMyTransactional() {
        jpaInsertUser("My annotaton transactional", 22);
        int error = 1 / 0;
        return "finish";
    }
}
