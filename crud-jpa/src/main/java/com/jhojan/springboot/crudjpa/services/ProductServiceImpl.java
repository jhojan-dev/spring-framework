package com.jhojan.springboot.crudjpa.services;

import com.jhojan.springboot.crudjpa.entities.Product;
import com.jhojan.springboot.crudjpa.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        return productRepository.findById(id)
                .map( productDB -> {
                    productDB.setName(product.getName()).setDescription(product.getDescription())
                            .setSku(product.getSku()).setPrice(product.getPrice());
                    return Optional.of(productRepository.save(productDB));
                }).orElse( Optional.empty() );
    }

    @Override
    @Transactional
    public Optional<Product> delete(Product product) {
        Optional<Product> optProduct = productRepository.findById(product.getId());
        optProduct.ifPresent(productRepository::delete);
        return optProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
