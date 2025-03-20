package com.jhojan.curso.springboot.interceptor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class FooController {

    @GetMapping("/foo")
    public Map<String, Object> foo() {
        return Map.of("message", "handler foo del controlador");
    }

    @GetMapping("/bar")
    public Map<String, Object> bar() {
        return Map.of("message", "handler bar del controlador");
    }

    @GetMapping("/baz")
    public Map<String, Object> baz() {
        return Map.of("message", "handler baz del controlador");
    }

}
