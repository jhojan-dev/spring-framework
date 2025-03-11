package com.jhojan.springboot.di.app.services;

import com.jhojan.springboot.di.app.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceFoo implements ProductService {
    @Override
    public List<Product> findAll() {
        return List.of(
                new Product(1L, "Google Pixel 9 Pro XL", 900L)
        );
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Google Pixel 9 Pro XL", 900L);
    }
}
