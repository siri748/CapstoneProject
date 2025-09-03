package com.wipro.siri.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;      // e.g., PATIENT_REGISTERED
    private String entityType;     // e.g., patient
    private String entityId;       // e.g., 123
    private String description;    // e.g., "New patient registered successfully"
    private LocalDateTime timestamp;
}
