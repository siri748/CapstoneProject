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
    private LocalDate patientDob;
    private String patientGender;
    private Integer patientHeight;
    private Integer patientWeight;
}

