package com.jabhay2012.ShoppingCart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabhay2012.ShoppingCart.entities.CartItem;
import com.jabhay2012.ShoppingCart.entities.ShoppingCart;
import com.jabhay2012.ShoppingCart.repos.CartItemRepository;
import com.jabhay2012.ShoppingCart.repos.ShoppingCartRepository;

@Service
public class ShoppingCartService {
    @Autowired 
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    public ShoppingCart findByUserId(Long userId) {
        return shoppingCartRepository.findById(userId).orElse(null);
    }
    

    public CartItem addItemToCart(ShoppingCart shoppingCart, CartItem cartItem){
        cartItem.setShoppingCart(shoppingCart);
        return cartItemRepository.save(cartItem);
    }

   

    public void removeItemFromCart(Long itemId){
        cartItemRepository.deleteById(itemId);;
    }

   
    
   public List<CartItem> getItems(Long cartId){
    ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
    return shoppingCart != null? shoppingCart.getItems() : null;
   }

   

    public ShoppingCart save(ShoppingCart ShoppingCart){
        return shoppingCartRepository.save(ShoppingCart);
    }

}
