package com.wipro.siri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto{
    private Long patientId;
    private String patientName;
    private String patientAddress;
    private LocalDate patientDob;
    private String patientGender;
    private Integer patientHeight;
    private Integer patientWeight;
    private List<MedicalRecordDto> medicalRecords; // optional if you need them
}
