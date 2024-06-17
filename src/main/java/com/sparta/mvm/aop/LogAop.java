package com.sparta.mvm.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j(topic = "AOP 로그")
@Component
@Aspect
public class LogAop {

    @Pointcut("execution (**com.sparta.mvm.controller..*.*(..))")
    private void controller(){}

    @Pointcut("execution (**com.sparta.mvm.controller.UserController.token*(..))")
    private void controller2(){}

    @Before("controller()")
    public void controllerLog(JoinPoint joinPoint){
        Object[] argList = joinPoint.getArgs();
        String requestURI = "";
        String requestMethod = "";
        for(Object obj : argList){
            if(obj instanceof HttpServletRequest) {
                requestURI = ((HttpServletRequest) obj).getRequestURI();
                requestMethod = ((HttpServletRequest) obj).getMethod();
            }
        }
        log.info("RequestURI : {}", requestURI);
        log.info("RequestMethod : {}", requestMethod);
    }

    @After("controller2()")
    public void testLog(){
        log.info("테스트입니다");
    }

    @Around("controller()")
    public Object testLog2(){
        log.info("테스트입니다2");
        return null;
    }
}
