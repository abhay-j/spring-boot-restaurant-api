package com.jabhay2012.ShoppingCart.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabhay2012.ShoppingCart.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
}
