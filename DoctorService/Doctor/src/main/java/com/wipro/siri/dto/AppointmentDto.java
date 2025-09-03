package com.wipro.siri.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private Status status; // BOOKED, WAITING, CALLED, RESOLVED, CANCELLED
    private String notes;

    public enum Status {
        BOOKED, WAITING, CALLED, RESOLVED, CANCELLED
    }
}
