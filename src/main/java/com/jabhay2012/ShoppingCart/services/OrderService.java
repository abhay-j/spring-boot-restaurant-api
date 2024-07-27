package com.jabhay2012.ShoppingCart.services;

import com.jabhay2012.ShoppingCart.dto.CartItemDTO;
import com.jabhay2012.ShoppingCart.dto.OrderDTO;
import com.jabhay2012.ShoppingCart.entities.*;
import com.jabhay2012.ShoppingCart.repos.OrderRepository;
import com.jabhay2012.ShoppingCart.repos.ProductRepository;
import com.jabhay2012.ShoppingCart.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //we have to use these objects that represent each table
    //create //update //read //delete
    public Order createOrder(OrderDTO orderDTO, String sessionId){

        Order order = new Order();
        order.setSessionId(sessionId);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());



        Optional<UserEntity> userOptional = userRepository.findByEmail(orderDTO.getEmail());
        UserEntity user = userOptional.orElseGet(() -> createGuestUser(orderDTO.getEmail()));
        order.setUser(user);

        //process cartItems -> start here
        List<CartItem> cartItems = new ArrayList<>();
//
        for (CartItemDTO itemDTO : orderDTO.getItems())
        {
            Product product = productRepository.findById(itemDTO.getProductId())
                                .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductId()));


            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(itemDTO.getQuantity());
            cartItem.setOrder(order);
            cartItems.add(cartItem);
        }
        order.setItems(cartItems);
        return orderRepository.save(order);


    }
    private UserEntity createGuestUser(String email) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setGuest(true);
        newUser.setUsername("Guests");
        newUser.setPassword(passwordEncoder.encode("GUEST_" + UUID.randomUUID().toString()));
        // Set other necessary fields for a new user
        return userRepository.save(newUser);

    }

}
