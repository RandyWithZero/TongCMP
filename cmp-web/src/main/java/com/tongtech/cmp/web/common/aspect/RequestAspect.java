package com.tongtech.cmp.web.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller HTTP请求，添加统一日志切面
 *
 * @author wangshaoqi
 * @date 2018/10/16 10:02
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    /**
     * Controller切入记录log
     */
    @Pointcut(value = "execution(* com.tongtech.cmp.web.controller.*.*(..))")
    public void log() {
    }

    /**
     * 请求开始记录log：URL、方法、IP、类名、参数
     *
     * @param joinPoint 切入点
     */
    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获得请求类名 // 具体方法 className + "." + joinPoint.getSignature()
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info(">>>>>请求详情：url={},method={},ip={},className={},args={}", request.getRequestURL(), request.getMethod(), request.getRemoteAddr(), className, joinPoint.getArgs());
    }

    /**
     * 请求完成后，暂无处理
     */
    @After("log()")
    public void doAfter() {

    }

    /**
     * 请求返回成功记录log：实体数据
     *
     * @param object 返回对象
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doReturning(Object object) {
        log.info(">>>>>请求结果：{}", object);
    }

}
