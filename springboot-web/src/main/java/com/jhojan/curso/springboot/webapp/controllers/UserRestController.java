package com.jhojan.curso.springboot.webapp.controllers;

import com.jhojan.curso.springboot.webapp.models.User;
import com.jhojan.curso.springboot.webapp.models.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDTO details() {
        UserDTO userDTO = new UserDTO();
        userDTO.setTitle(null);
        userDTO.setUser(new User("Jhojan", "Mamani"));
        return userDTO;
    }

    @GetMapping("/list")
    public List<User> list() {
        return List.of(
                new User("Jhojan", "Mamani"),
                new User("Pepe", "Doe"),
                new User("John", "Doe")
        );
    }

    @GetMapping("/details-map")
    public Map<?,?> detailsMap() {
        return Map.of(
                "title", "Hello World",
                "user", new User("Jhojan", "Mamani")
        );
    }

}
