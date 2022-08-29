package com.fang.springboot.common.aspect;

import com.fang.springboot.common.util.TransactionalUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * 自定义事务切面
 * @author shaobin
 * @date 2022/8/27 16:03
 */
@Aspect
@Component
public class MyTransactionalAspect {

    @Autowired
    private TransactionalUtils transactionalUtils;

    /**
     * 环绕通知
     * 拦截注解，便可在标有此注解的方法前后进行环绕通知，比直接拦截方法要简易很多
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.fang.springboot.common.annotation.MyTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) {
        TransactionStatus begin = null;
        Object result = null;
        try {
            begin = transactionalUtils.begin();
            result = joinPoint.proceed();
            transactionalUtils.commit(begin);
        } catch (Throwable e) {
            e.printStackTrace();
            transactionalUtils.rollback(begin);
            return "fail";
        }
        return result;
    }
}
