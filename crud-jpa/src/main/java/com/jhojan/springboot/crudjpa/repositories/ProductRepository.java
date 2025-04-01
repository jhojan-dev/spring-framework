package com.jhojan.springboot.crudjpa.repositories;

import com.jhojan.springboot.crudjpa.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);

}
