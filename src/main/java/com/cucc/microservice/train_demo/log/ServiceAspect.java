package com.cucc.microservice.train_demo.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 记录service层的入参、耗时和返回
 */
@Aspect
@Component
public class ServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
    //定义切面：注解了“@Serivce”的类中所有的public方法
    @Pointcut("@within(org.springframework.stereotype.Service) && execution(public * *(..))")
    private void servicePointcut(){}

    @Before("servicePointcut()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("Service CLASS.METHOD \t : \t{}.{} , Args \t : \t {}",
                joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }
    @Around("servicePointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        long startTime = System.currentTimeMillis();
        try {
            Object ob = proceedingJoinPoint.proceed();// ob 为方法的返回值
            logger.info("Service Time Consuming \t : \t {}", (System.currentTimeMillis() - startTime));
            return ob;
        }catch (Throwable t){
            logger.info("Service Exception \t : \t {}:{}", t.getClass(), t.getMessage());
            logger.info("Service Time Consuming \t : \t {}", (System.currentTimeMillis() - startTime));
            throw t;
        }
    }
    @AfterReturning(returning = "ret", pointcut = "servicePointcut()")
    public void doAfterReturning(Object ret){
        logger.info("Service Return \t : \t {}", ret);
    }
} 