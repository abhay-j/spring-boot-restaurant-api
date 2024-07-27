package com.jabhay2012.ShoppingCart.repos;

import com.jabhay2012.ShoppingCart.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // you can add all sorts of admin specific methods here later
    Optional<Order> findBySessionId(String sessionId);
}
