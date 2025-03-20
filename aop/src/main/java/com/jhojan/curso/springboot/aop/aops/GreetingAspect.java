package com.jhojan.curso.springboot.aop.aops;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(GreetingAspect.class);

    @Before("GreetingServicePointCuts.greetingPointCut()")
    public void loggerBefore(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Antes de {} con los argumentos {}", methodName, args);
    }

    @After("GreetingServicePointCuts.greetingPointCut()")
    public void loggerAfter(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Despues de {} con los argumentos {}", methodName, args);
    }

    @AfterReturning("GreetingServicePointCuts.greetingPointCut()")
    public void loggerAfterReturning(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Despues de retornar {} con los argumentos {}", methodName, args);
    }

    @AfterThrowing("GreetingServicePointCuts.greetingPointCut()")
    public void loggerAfterThrowing(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        LOGGER.info("Despues de lanzar la excepcion {} con los argumentos {}", methodName, args);
    }

    @Around("GreetingServicePointCuts.greetingPointCut()")
    public Object loggerAround(final ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        LOGGER.info("El metodo {}() con los parametros {}", methodName, args);
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error("Error en el metodo {}()", methodName);
            throw new RuntimeException(e);
        }
        LOGGER.info("El metodo {}() retorna el resultado: ", result);
        return result;
    }
}
