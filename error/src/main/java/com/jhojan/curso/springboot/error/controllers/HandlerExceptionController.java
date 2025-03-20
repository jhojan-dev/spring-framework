package com.jhojan.curso.springboot.error.controllers;

import com.jhojan.curso.springboot.error.exceptions.UserNotFoundException;
import com.jhojan.curso.springboot.error.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class, NumberFormatException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error divisionByZero(final Exception ex) {
        return new Error(
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                500,
                new Date()
        );
    }

    @ExceptionHandler({NoHandlerFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Error> notFoundException(final Exception ex) {
        return ResponseEntity
                .status(404)
                .body( new Error(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        404,
                        new Date())
                );
    }

}
