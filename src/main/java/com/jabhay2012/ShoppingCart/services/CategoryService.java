package com.jabhay2012.ShoppingCart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabhay2012.ShoppingCart.entities.Category;
import com.jabhay2012.ShoppingCart.repos.CategoryRepository;
@Service
public class CategoryService {
//    @Autowired
//     private CategoryRepository categoryRepository;

//     public List<Category> findAll() {
//         return categoryRepository.findAll();
//     }

//     public Category findById(Long id) {
//         return categoryRepository.findById(id).orElse(null);
//     }

//     public Category save(Category category) {
//         return categoryRepository.save(category);
//     }

//     public void delete(Long id) {
//         categoryRepository.deleteById(id);
//     } 

     @Autowired
     private CategoryRepository categoryRepository;

     public List<Category> findAll(){
        return categoryRepository.findAll();
     }

     public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
     }

     public Category save(Category category){
        return categoryRepository.save(category);
     }

     public void delete(Long id){
        categoryRepository.deleteById(id);
     }
}
