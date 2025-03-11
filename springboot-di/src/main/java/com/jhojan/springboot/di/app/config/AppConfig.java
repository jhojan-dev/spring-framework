package com.jhojan.springboot.di.app.config;

import com.jhojan.springboot.di.app.repositories.ProductRepository;
import com.jhojan.springboot.di.app.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean
    @Primary
    public ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson();
    }

}
