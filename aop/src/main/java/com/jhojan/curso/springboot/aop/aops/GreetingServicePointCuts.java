package com.jhojan.curso.springboot.aop.aops;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCuts {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution( * com.jhojan.curso.springboot.aop.services.GreetingService.*(..) )")
    public void greetingPointCut() { }

    @Pointcut("execution( * com.jhojan.curso.springboot.aop.services.GreetingService.*(..) )")
    public void greetingFooAspectPointCut() {}

}
