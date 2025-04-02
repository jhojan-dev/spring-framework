package com.jhojan.springboot.crudjpa.services;

import com.jhojan.springboot.crudjpa.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

}
