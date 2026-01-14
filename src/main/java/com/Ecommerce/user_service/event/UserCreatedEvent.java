package com.Ecommerce.user_service.event;
import java.time.Instant;

public class UserCreatedEvent {

    private final String userId;
    private final String email;
    private final Instant occurredAt = Instant.now();

    public UserCreatedEvent(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public Instant getOccurredAt() { return occurredAt; }
}
