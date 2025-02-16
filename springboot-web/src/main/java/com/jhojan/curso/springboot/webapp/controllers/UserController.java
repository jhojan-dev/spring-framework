package com.jhojan.curso.springboot.webapp.controllers;

import com.jhojan.curso.springboot.webapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Jhojan", "Mamani", "jmamanig@mail.com");
        model.addAttribute("message", "Hello World");
        model.addAttribute("user", user);
       return "details";
   }

   @GetMapping("/list")
    public String list(ModelMap model) {
       model.addAttribute("title", "List of users");
       return "list";
   }

   @ModelAttribute("users")
   private List<User> usersModel() {
        return List.of(
                new User("Jhojan", "Mamani"),
                new User("Pepe", "Doe"),
                new User("John", "Doe")
        );
   }

}
