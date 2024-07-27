package com.jabhay2012.ShoppingCart.dto;

public class CartItemDTO {
    private Long productId;
    private int quantity;

    // Default constructor
    public CartItemDTO() {}

    // Constructor with all fields
    public CartItemDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
