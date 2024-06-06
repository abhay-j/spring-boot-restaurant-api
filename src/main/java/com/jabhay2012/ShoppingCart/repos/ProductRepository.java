package com.jabhay2012.ShoppingCart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabhay2012.ShoppingCart.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
