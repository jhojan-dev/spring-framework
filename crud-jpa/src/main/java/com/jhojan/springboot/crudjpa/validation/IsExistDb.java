package com.jhojan.springboot.crudjpa.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsExistDbValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistDb {

    String message() default "Ya existe en la base de datos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
