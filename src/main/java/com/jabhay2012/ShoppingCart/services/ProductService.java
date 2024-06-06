package com.jabhay2012.ShoppingCart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabhay2012.ShoppingCart.entities.Product;
import com.jabhay2012.ShoppingCart.repos.ProductRepository;
@Service
public class ProductService {
    //  @Autowired
    // private ProductRepository productRepository;

    // public List<Product> findAll() {
    //     return productRepository.findAll();
    // }

    // public Product findById(Long id) {
    //     return productRepository.findById(id).orElse(null);
    // }

    // public Product save(Product product) {
    //     return productRepository.save(product);
    // }

    // public void delete(Long id) {
    //     productRepository.deleteById(id);
    // }

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
         productRepository.deleteById(id);
    }

}
