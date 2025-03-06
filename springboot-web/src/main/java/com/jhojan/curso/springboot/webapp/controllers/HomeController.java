package com.jhojan.curso.springboot.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "/home"})
    public String home() {
        // return "forward:/details"; <- mantiene el request
        return "redirect:/list";
    }

}
