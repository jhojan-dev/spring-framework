package com.jhojan.curso.springboot.di.factura.config;

import com.jhojan.curso.springboot.di.factura.models.Item;
import com.jhojan.curso.springboot.di.factura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {

    @Bean("default")
    List<Item> itemsInvoice(){
        Product product1 = new Product();
        product1.setName("Camara Sony");
        product1.setPrice(800);

        Product product2 = new Product();
        product2.setName("Bicicleta B2");
        product2.setPrice(800);

        return Arrays.asList( new Item(product1, 2), new Item(product2, 4) );
    }

    @Bean
    @Primary
    List<Item> itemsInvoiceOffice() {
        Product product1 = new Product("Monitor Asus 24", 700);
        Product product2 = new Product("Notebook Razer", 2400);
        Product product3 = new Product("Impresora HP", 800);
        Product product4 = new Product("Escritorio Oficina", 900);
        return Arrays.asList(
                new Item(product1, 4),
                new Item(product2, 6),
                new Item(product3, 1),
                new Item(product4, 4)
        );
    }

}
