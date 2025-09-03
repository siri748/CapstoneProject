package com.wipro.siri.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAscept {

    // Logs every method in any class under "controller" package
    @Before("execution(* com.example.demo.controller..*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        log.info("===== Logging Before Method Call =====");
        log.info("Class: {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("Method: {}", joinPoint.getSignature().getName());
        log.info("======================================");
        
    }
}