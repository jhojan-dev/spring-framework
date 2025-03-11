package com.jhojan.springboot.di.app.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhojan.springboot.di.app.models.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> products;

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("data/products.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            products = Arrays.asList( mapper.readValue(resource.getFile(), Product[].class) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
