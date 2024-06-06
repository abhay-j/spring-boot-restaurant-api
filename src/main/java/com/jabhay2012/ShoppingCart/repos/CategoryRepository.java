package com.jabhay2012.ShoppingCart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabhay2012.ShoppingCart.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
