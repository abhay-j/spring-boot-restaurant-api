package com.jabhay2012.ShoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jabhay2012.ShoppingCart.entities.Product;
import com.jabhay2012.ShoppingCart.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

   @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")
   // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
   @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")
   // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")
   // @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

        @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")
   // @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        product.setTitle(productDetails.getTitle());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());

        Product updatedProduct = productService.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
   @CrossOrigin(origins = "https://shopping-cart-client-80fae8a6e96a.herokuapp.com")
    //@CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
