package com.jhojan.curso.springboot.error.services;

import com.jhojan.curso.springboot.error.models.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users;

    public UserServiceImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter( user -> user.id().equals( id ) ).findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

}
