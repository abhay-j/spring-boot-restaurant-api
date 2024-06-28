package com.jabhay2012.ShoppingCart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabhay2012.ShoppingCart.entities.Role;
import com.jabhay2012.ShoppingCart.repos.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByrole(String name) {
        return roleRepository.findByName(name);
    }

}
