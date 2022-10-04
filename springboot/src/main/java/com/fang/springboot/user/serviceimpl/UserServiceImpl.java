package com.fang.springboot.user.serviceimpl;

import com.fang.springboot.common.annotation.MyTransactional;
import com.fang.springboot.user.dao.UserDAO;
import com.fang.springboot.user.service.UserService;
import com.fang.springboot.common.util.TransactionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author shaobin
 * @date 2022/8/5 20:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    TransactionalUtils transactionalUtils;

    @Override
    public int insertUser(String name, Integer age) {
        return userDAO.insertUser(name, age);
    }

    @Override
    @Transactional
    public String testSpringAnnotationTransactional() {
        try {
            insertUser("transactional", 22);
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
            insertUser("transactional", 24);
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
        insertUser("My annotaton transactional", 22);
        int error = 1 / 0;
        return "finish";
    }
}
