package com.jhojan.curso.springboot.error.models;

import java.util.Date;

public record Error(
        String message,
        String error,
        int status,
        Date timestamp
) {}
