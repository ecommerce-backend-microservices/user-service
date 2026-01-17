package com.Ecommerce.user_service.event;
import java.time.Instant;

public class UserCreatedEvent {

    private final Long userId;
    private final String email;
    private final Instant occurredAt = Instant.now();

    public UserCreatedEvent(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() { return email; }
    public Instant getOccurredAt() { return occurredAt; }
}
