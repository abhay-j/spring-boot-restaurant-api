package com.jabhay2012.ShoppingCart.dto;

import java.util.List;

public class OrderDTO {
    private String email;
    private List<CartItemDTO> items;
    private String shippingAddress;

    // Default constructor
    public OrderDTO() {}

    // Constructor with all fields
    public OrderDTO(String email, List<CartItemDTO> items) {
        this.email = email;
        this.items = items;
        this.shippingAddress = null;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
