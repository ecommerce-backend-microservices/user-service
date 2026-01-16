package com.Ecommerce.user_service.service;

import com.Ecommerce.user_service.domain.User;
import com.Ecommerce.user_service.domain.UserStatus;
import com.Ecommerce.user_service.dto.CreateUserRequest;
import com.Ecommerce.user_service.dto.LoginUserRequest;
import com.Ecommerce.user_service.dto.UserResponse;
import com.Ecommerce.user_service.event.UserCreatedEvent;
import com.Ecommerce.user_service.exception.InvalidCredentialsException;
import com.Ecommerce.user_service.exception.UserAlreadyExistsException;
import com.Ecommerce.user_service.exception.UserBlockedException;
import com.Ecommerce.user_service.producer.UserEventProducer;
import com.Ecommerce.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEventProducer eventProducer;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserEventProducer eventProducer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventProducer = eventProducer;
    }

    @Override
    public UserResponse register(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        String passwordHash = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getEmail(),
                passwordHash,
                request.getName(),
                request.getPhone()
        );

        User savedUser = userRepository.save(user);

        eventProducer.publishUserCreated(
                new UserCreatedEvent(savedUser.getId(), savedUser.getEmail())
        );

        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName()
        );
    }

    @Override
    public String login(LoginUserRequest loginUserRequest) {

        User user = userRepository.findByEmail(loginUserRequest.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        // Check user status
        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new UserBlockedException("User account is blocked");
        }

        // Validate password (DO NOT encode again)
        boolean passwordMatches = passwordEncoder.matches(
                loginUserRequest.getPassword(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return "LOGIN_SUCCESS";
    }

}

