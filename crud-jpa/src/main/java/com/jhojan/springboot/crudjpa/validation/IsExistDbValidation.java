package com.jhojan.springboot.crudjpa.validation;

import com.jhojan.springboot.crudjpa.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String> {

    private final ProductService productService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (productService == null) return true;
        return !productService.existsBySku(s);
    }
}
