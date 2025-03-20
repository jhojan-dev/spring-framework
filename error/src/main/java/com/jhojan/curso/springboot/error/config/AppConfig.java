package com.jhojan.curso.springboot.error.config;

import com.jhojan.curso.springboot.error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    List<User> users() {
        return Arrays.asList(
            new User(1L, "Pepe", "Gonzales"),
            new User(2L, "Andres", "Mena"),
            new User(3L, "Maria", "Perez"),
            new User(4L, "Josefa", "Ramirez"),
            new User(5L, "Ale", "Gutierrez")
        );
    }

}
