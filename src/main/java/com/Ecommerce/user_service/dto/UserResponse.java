package com.Ecommerce.user_service.dto;

public class UserResponse {

    private final Long userId;
    private final String email;
    private final String name;

    public UserResponse(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}

