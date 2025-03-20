package com.jhojan.curso.springboot.error.controllers;

import com.jhojan.curso.springboot.error.exceptions.UserNotFoundException;
import com.jhojan.curso.springboot.error.models.domain.User;
import com.jhojan.curso.springboot.error.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        // int value = 100/ 0;
        int value = Integer.parseInt("10x");
        return "Ok 200";
    }

    @GetMapping("/show/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User show(@PathVariable long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }




}
