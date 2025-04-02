package com.jhojan.springboot.crudjpa.entities;

import com.jhojan.springboot.crudjpa.validation.IsExistDb;
import com.jhojan.springboot.crudjpa.validation.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "PRODUCTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{NotEmpty.product.name}")
    @Size(min = 3, max = 100)
    private String name;

    @Min(500)
    @NotNull(message = "{NotEmpty.product.price}")
    private Integer price;

    @IsRequired()
    private String description;

    @IsRequired
    @IsExistDb
    private String sku;

}
