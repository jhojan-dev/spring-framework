package com.jhojan.curso.springboot.error.models.domain;

public record User(
        Long id,
        String name,
        String lastName
        // Role role
) { }
