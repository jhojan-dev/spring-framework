package com.jhojan.springboot.crudjpa.validation;

import com.jhojan.springboot.crudjpa.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(userService == null) return true;
        return !userService.existsByUsername(s);
    }
}
