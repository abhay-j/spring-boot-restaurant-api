package com.jabhay2012.ShoppingCart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabhay2012.ShoppingCart.entities.CartItem;
import com.jabhay2012.ShoppingCart.entities.ShoppingCart;
import com.jabhay2012.ShoppingCart.repos.CartItemRepository;
import com.jabhay2012.ShoppingCart.repos.ShoppingCartRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
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

   

//    public void removeItemFromCart(Long itemId){
//        cartItemRepository.deleteById(itemId);;
//    }

    @Transactional
    public void removeItemFromCart(Long cartId, Long itemId) {
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
        if (cartOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found with id: " + cartId);
        }
        ShoppingCart cart = cartOptional.get();

        Optional<CartItem> itemOptional = cartItemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found with id: " + itemId);
        }
        CartItem itemToRemove = itemOptional.get();

        if (!itemToRemove.getShoppingCart().getId().equals(cartId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item does not belong to the specified cart");
        }

        cart.getItems().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);
        shoppingCartRepository.save(cart);
    }

    
   public List<CartItem> getItems(Long cartId){
    ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
    return shoppingCart != null? shoppingCart.getItems() : null;
   }

   

    public ShoppingCart save(ShoppingCart ShoppingCart){
        return shoppingCartRepository.save(ShoppingCart);
    }

}
