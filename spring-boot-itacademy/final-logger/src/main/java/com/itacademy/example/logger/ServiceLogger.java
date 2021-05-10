package com.itacademy.example.logger;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class ServiceLogger {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogger.class);

    @AfterThrowing(pointcut = "execution(* com.itacademy.example.service.*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        logger.error(exception.getMessage());
    }
}
