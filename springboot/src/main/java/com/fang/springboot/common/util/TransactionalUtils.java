package com.fang.springboot.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author shaobin
 * @date 2022/8/27 15:11
 */
@Component
@DependsOn(value = {"transactionManager"})
public class TransactionalUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     * @return
     */
    public TransactionStatus begin() {
        return dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     * @param transactionStatus
     */
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
