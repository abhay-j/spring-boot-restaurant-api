package com.jabhay2012.ShoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCartByUserId(@PathVariable Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(userId);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createCartForUser(@RequestBody UserEntity user) {
        UserEntity existingUser = userService.findById(user.getId());
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(existingUser);
        ShoppingCart savedCart = shoppingCartService.save(shoppingCart);
        return ResponseEntity.ok(savedCart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(cartId);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }
        CartItem addedItem = shoppingCartService.addItemToCart(shoppingCart, cartItem);
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getItemsInCart(@PathVariable Long cartId) {
        List<CartItem> items = shoppingCartService.getItems(cartId);
        if (items == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long itemId) {
        shoppingCartService.removeItemFromCart(itemId);
        return ResponseEntity.noContent().build();
    }
}
