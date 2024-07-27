package com.jabhay2012.ShoppingCart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

   // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/")
    public String sayHello(){
        return "Test, Hello";
    }
}
