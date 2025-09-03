package com.wipro.siri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto{
    private Long recordId;
    private String diagnosis;
    private LocalDate visitDate;
    private List<MedicineDto> medicines; // Include if Patient Service returns medicines
}
