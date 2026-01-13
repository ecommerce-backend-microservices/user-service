package com.Ecommerce.user_service.service;

import com.Ecommerce.user_service.domain.User;
import com.Ecommerce.user_service.dto.LoginRequest;
import com.Ecommerce.user_service.dto.RegisterRequest;
import com.Ecommerce.user_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email)) {
            return "Email already registered";
        }

        User user = new User();
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email)
                .orElse(null);

        if (user == null) {
            return "Invalid email or password";
        }

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            return "Invalid email or password";
        }

        return "Login successful";
    }
}
