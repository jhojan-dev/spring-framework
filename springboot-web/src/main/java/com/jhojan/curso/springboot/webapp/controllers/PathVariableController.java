package com.jhojan.curso.springboot.webapp.controllers;

import com.jhojan.curso.springboot.webapp.models.User;
import com.jhojan.curso.springboot.webapp.models.dto.ParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.code}")
    private Integer code;
    @Value("${config.username}")
    private String username;

    @Value("#{${config.list-of-values}}") // Spring Expression Language
    private Map<String, Object> listOfValues;

    @Value("#{${config.valuesMap}.product}")
    private String productProperty;

    @Value("#{${config.valuesMap}.description}")
    private String descriptionProperty;

    @Value("#{${config.valuesMap}.price}")
    private String priceProperty;

    @Autowired
    private Environment env;

    @GetMapping("/baz/{message}")
    public ParamDTO baz(@PathVariable String message) {
        ParamDTO param = new ParamDTO();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVariable(@PathVariable String product, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("username", username);
        json.put("message", message);
        json.put("message2", env.getProperty("config.message2"));
        json.put("lifeDetails", listOfValues);
        json.put("productProperty", productProperty);
        json.put("descriptionProperty", descriptionProperty);
        json.put("priceProperty", priceProperty);
        return json;
    }

}
