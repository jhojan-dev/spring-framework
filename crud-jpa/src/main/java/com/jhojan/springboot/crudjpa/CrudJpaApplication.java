package com.jhojan.springboot.crudjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class CrudJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudJpaApplication.class, args);
    }

}
