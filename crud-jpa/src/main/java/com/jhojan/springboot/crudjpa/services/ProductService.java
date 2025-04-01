package com.jhojan.springboot.crudjpa.services;

import com.jhojan.springboot.crudjpa.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product create(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Product product);

    boolean existsBySku(String sku);

}