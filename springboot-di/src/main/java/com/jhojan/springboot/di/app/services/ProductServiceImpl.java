package com.jhojan.springboot.di.app.services;

import com.jhojan.springboot.di.app.models.Product;
import com.jhojan.springboot.di.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Value("${config.price-taxt}") private Double priceTax;

    public ProductServiceImpl( ProductRepository repository ) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(product -> {
                    Double priceTax = product.getPrice() * this.priceTax;
                    Product newProduct = (Product) product.clone();
                    newProduct.setPrice(priceTax.longValue());
                    return newProduct;
//                    product.setPrice(priceTax.longValue());
//                    return product;
                }).toList();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
