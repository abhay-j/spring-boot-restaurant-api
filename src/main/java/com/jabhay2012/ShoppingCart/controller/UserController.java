package com.jabhay2012.ShoppingCart.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jabhay2012.ShoppingCart.entities.User;
import com.jabhay2012.ShoppingCart.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    
 
   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id){
    User user = userService.findById(id);
    if(user == null){
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
   } 
   

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        User user = userService.findById(id);
        if(user == null){
            return ResponseEntity.noContent().build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}