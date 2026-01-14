package com.Ecommerce.user_service.dto;

public class UserResponse {

    private final String userId;
    private final String email;
    private final String name;

    public UserResponse(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}

