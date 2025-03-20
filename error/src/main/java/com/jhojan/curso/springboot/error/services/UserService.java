package com.jhojan.curso.springboot.error.services;

import com.jhojan.curso.springboot.error.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    List<User> findAll();
}
