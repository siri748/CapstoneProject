package com.wipro.siri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage {
    private String eventType;    // e.g., PATIENT_REGISTERED
    private String entityType;   // e.g., patient
    private String entityId;     // e.g., 123
    private String description;  // e.g., "Patient registered successfully"
}
