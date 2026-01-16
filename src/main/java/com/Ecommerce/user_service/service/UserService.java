package com.Ecommerce.user_service.service;

import com.Ecommerce.user_service.dto.CreateUserRequest;
import com.Ecommerce.user_service.dto.LoginUserRequest;
import com.Ecommerce.user_service.dto.UserResponse;

public interface UserService {

    UserResponse register(CreateUserRequest request);
    String login(LoginUserRequest loginUserRequest);
}
