package com.jabhay2012.ShoppingCart.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabhay2012.ShoppingCart.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
