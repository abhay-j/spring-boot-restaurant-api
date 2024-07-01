package com.jabhay2012.ShoppingCart.dto;

public class LoginResponseDto {
    private String accessToken;
    private String tokenType;
    private Long id;
    private String username;
    private String email;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken, String tokenType, Long id, String username, String email) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
