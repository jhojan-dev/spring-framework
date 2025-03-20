package com.jhojan.curso.springboot.error.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message) {
        super(message);
    }
}
