package com.fang.springboot.user.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author shaobin
 * @date 2022/10/8 21:51
 */
@Component
@Aspect
@Slf4j
public class UserRequestLogAspect {
    @Pointcut("execution(* com.fang.springboot.user.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore() {
        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求日志
        log.info("URL:{}", request.getRequestURL().toString());
        log.info("HTTP_METHOD:{}", request.getMethod());
        log.info("IP:{}", request.getRemoteAddr());
        Enumeration<String> parameterNames = request.getParameterNames();
        String params = "";
        while (parameterNames.hasMoreElements()) {
            params += parameterNames.nextElement() + " ";
        }
        log.info("PARAMS:{}", params);
    }

    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) {
        log.info("RESPONSE:{}", ret);
    }
}
