package com.wipro.siri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto {
    private Long recordId;
    private String diagnosis;
    private String visitDate; // Or LocalDate
    private Long patientId;
    private List<MedicineDto> medicines; // Nested medicines for this record
}
