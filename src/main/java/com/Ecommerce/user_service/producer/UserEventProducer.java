package com.Ecommerce.user_service.producer;

import com.Ecommerce.user_service.event.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private static final String TOPIC = "user.events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserCreated(UserCreatedEvent event) {
            kafkaTemplate.send(TOPIC, String.valueOf(event.getUserId()), event);
    }
}
