package com.jhojan.curso.springboot.aop.services;


public interface GreetingService {

    String sayHello(String person, String phrase);
    String sayHelloError(String person, String phrase);

}
