package com.jabhay2012.ShoppingCart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabhay2012.ShoppingCart.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
