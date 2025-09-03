package com.wipro.siri.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.siri.services.AuditService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditKafkaConsumer {

    private final AuditService auditService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuditKafkaConsumer(AuditService auditService) {
        this.auditService = auditService;
    }

    @KafkaListener(topics = "patient-topic", groupId = "audit-group")
    public void consumePatientEvent(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText();
            String entityType = jsonNode.get("entityType").asText();
            String entityId = jsonNode.get("entityId").asText();
            String description = jsonNode.get("description").asText();

            auditService.saveAuditLog(eventType, entityType, entityId, description);
            System.out.println("✅ Saved Audit Log for event: " + eventType);
        } catch (Exception e) {
            System.err.println("❌ Error parsing JSON message: " + e.getMessage());
        }
    }
}
