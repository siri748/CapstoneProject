package com.wipro.siri.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingDto {
    private Long id;
    private Long patientId;
    private Long appointmentId;
    private Double amount;
    private String status; // PAID, PENDING, CANCELLED
    private LocalDate billingDate;
}
