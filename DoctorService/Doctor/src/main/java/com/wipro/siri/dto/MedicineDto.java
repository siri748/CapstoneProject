package com.wipro.siri.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long medicineId;
    private String name;
    private String description;
    private String dosage;
    private double price;
}

