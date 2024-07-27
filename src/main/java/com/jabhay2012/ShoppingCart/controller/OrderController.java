package com.jabhay2012.ShoppingCart.controller;

import com.jabhay2012.ShoppingCart.dto.OrderDTO;
import com.jabhay2012.ShoppingCart.entities.Order;
import com.jabhay2012.ShoppingCart.repos.OrderRepository;
import com.jabhay2012.ShoppingCart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO, @RequestHeader("Session-ID") String sessionId){
        Order createdOrder = orderService.createOrder(orderDTO,sessionId);
        return ResponseEntity.ok(createdOrder);
    }
}
