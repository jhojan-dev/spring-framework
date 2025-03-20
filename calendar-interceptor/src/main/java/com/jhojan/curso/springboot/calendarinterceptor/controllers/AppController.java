package com.jhojan.curso.springboot.calendarinterceptor.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {

        return ResponseEntity
                .ok()
                .body(
                        Map.of("title", "Bienvenidos al sistema de atencion",
                                "time", new Date(),
                                "message", request.getAttribute("message")
                        )
                );
    }

}
