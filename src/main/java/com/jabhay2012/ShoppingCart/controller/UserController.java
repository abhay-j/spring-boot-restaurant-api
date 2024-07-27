package com.jabhay2012.ShoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jabhay2012.ShoppingCart.entities.UserEntity;
import com.jabhay2012.ShoppingCart.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //@CrossOrigin(origins = "http://localhost:5173")
    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    //@CrossOrigin(origins = "http://localhost:5173")
    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

    //@CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

   // @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setUsername(userDetails.getUsername());

        user.setEmail(userDetails.getEmail());
        UserEntity updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

   // @CrossOrigin(origins = "http://localhost:5173")
   @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}