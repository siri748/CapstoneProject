package com.wipro.siri.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.siri.dto.EventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void publishPatientEvent(EventMessage eventMessage) {
        try {
            String message = objectMapper.writeValueAsString(eventMessage);
            kafkaTemplate.send("patient-topic", message);
            System.out.println("Sent Patient Event: " + message);
        } catch (Exception e) {
            System.err.println("Error sending Kafka message: " + e.getMessage());
        }
    }
}
