package com.jhojan.curso.springboot.aop.controllers;

import com.jhojan.curso.springboot.aop.services.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(
                Map.of("greeting", greetingService.sayHello("Pepe", "Hola que tal!"))
        );
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(
                Map.of("greeting", greetingService.sayHelloError("Pepe", "Hola que tal!"))
        );
    }
}
