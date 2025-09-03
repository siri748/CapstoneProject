package com.wipro.siri.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Logs every method in any class under "controller" package in this service
    @Before("execution(* com.wipro.siri.controller..*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        log.info("===== Logging Before Method Call =====");
        log.info("Class: {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("Method: {}", joinPoint.getSignature().getName());
        log.info("======================================");
    }
}
