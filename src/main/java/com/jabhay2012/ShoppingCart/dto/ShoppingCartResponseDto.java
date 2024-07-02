package com.jabhay2012.ShoppingCart.dto;

public class ShoppingCartResponseDto {
    private boolean success;
    private String message;
    private Long cartId;

    public ShoppingCartResponseDto() {
    }

    public ShoppingCartResponseDto(boolean success, String message, Long cartId) {
        this.success = success;
        this.message = message;
        this.cartId = cartId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
