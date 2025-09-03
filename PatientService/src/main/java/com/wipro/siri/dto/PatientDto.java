package com.wipro.siri.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long patientId;
    private String patientName;
    private String patientAddress;
    private String patientGender;
    private LocalDate patientDob; // Keeping as String for simplicity
    private int patientWeight;
    private int patientHeight;
}
