package com.jhojan.springboot.crudjpa.validation;

import com.jhojan.springboot.crudjpa.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"Es requerido!");
        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", "Es requerido, por favor");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "No puede ser nulo, ok!");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "Debe ser valor numerico o igual que 500!");
        }
    }
}
