package com.jabhay2012.ShoppingCart.controller;

import java.util.List;

import com.jabhay2012.ShoppingCart.dto.ShoppingCartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jabhay2012.ShoppingCart.entities.CartItem;
import com.jabhay2012.ShoppingCart.entities.ShoppingCart;
import com.jabhay2012.ShoppingCart.entities.UserEntity;
import com.jabhay2012.ShoppingCart.services.ShoppingCartService;
import com.jabhay2012.ShoppingCart.services.UserService;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCartByUserId(@PathVariable Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(userId);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shoppingCart);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{userId}")
    public ResponseEntity<ShoppingCartResponseDto> createCartForUser(@PathVariable Long userId) {
        UserEntity existingUser = userService.findById(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ShoppingCartResponseDto(false, "User not found", null));
        }
        // Check if a shopping cart already exists for the user
        ShoppingCart existingCart = shoppingCartService.findByUserId(userId);
        if (existingCart != null) {
            // If a cart exists, return it
            return ResponseEntity
                    .ok(new ShoppingCartResponseDto(true, "existing cart retrived", existingCart.getId() ));
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(existingUser);
        ShoppingCart savedCart = shoppingCartService.save(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ShoppingCartResponseDto(true, "New cart created", savedCart.getId()));
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(cartId);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }
        CartItem addedItem = shoppingCartService.addItemToCart(shoppingCart, cartItem);
        return ResponseEntity.ok(addedItem);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getItemsInCart(@PathVariable Long cartId) {
        List<CartItem> items = shoppingCartService.getItems(cartId);
        if (items == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        shoppingCartService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("/items/{itemId}")
//    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long itemId) {
//        shoppingCartService.removeItemFromCart(itemId);
//        return ResponseEntity.noContent().build();
//    }
}
