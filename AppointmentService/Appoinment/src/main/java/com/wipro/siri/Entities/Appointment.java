package com.wipro.siri.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relation with Patient
    private Long patientId;

    // relation with Doctor
    private Long doctorId;

    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status; // BOOKED, WAITING, CALLED, RESOLVED, CANCELLED

    private String notes;

    public enum Status {
        BOOKED, WAITING, CALLED, RESOLVED, CANCELLED
    }
}