package com.jhojan.curso.springboot.aop.aops;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Before("GreetingServicePointCuts.greetingFooAspectPointCut()")
    public void loggerBefore(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Antes Primero: {} invocado con los parametros {}", methodName, args);
    }

    @After("GreetingServicePointCuts.greetingFooAspectPointCut()")
    public void loggerAfter(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Despues Primero: {} invocado con los parametros {}", methodName, args);
    }

}
