package com.cucc.microservice.train_demo.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 记录controller的请求信息、耗时、返回信息到日志
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    //定义切面：位于文件目录com.cuss.microservice.web下 并且注解了“@RequestMapping”的方法
/*    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "&& within(com.cucc.microservice.train_demo.*)")*/
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerPointCut() { }

    @Before("controllerPointCut()")
    public void doBefore(JoinPoint joinPoint)  {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info(
                "\n Controller Request : \n " +
                        "\t Header(CBSS-MS-GRAY) \t : \t {}\n" +
                        "\t Controller RemoteIP \t : \t {} \n" +
                        "\t RequestURL \t : \t {} \n" +
                        "\t HTTP METHOD \t : \t {} \n" +
                        "\t CLASS.METHOD \t : \t {}.{} \n" +
                        "\t Args \t : \t {}",
                request.getHeader("CBSS-MS-GRAY"),
                request.getRemoteAddr(),
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
        }

        @AfterReturning(returning = "ret", pointcut = "controllerPointCut()")
        public void doAfterReturning(Object ret)  {
            // 处理完请求，返回内容
            logger.info("Controller Return \t : \t {}", ret);
        }

        @Around("controllerPointCut()")
        public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
            long startTime = System.currentTimeMillis();
            Object ob = pjp.proceed();// ob 为方法的返回值
            logger.info("Controller Time Consuming \t : \t {}", (System.currentTimeMillis() - startTime));
            return ob;
        }

    }
