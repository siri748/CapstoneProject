package com.wipro.siri.services;

import com.wipro.siri.dto.MedicalRecordDto;
import com.wipro.siri.dto.MedicineDto;
import java.util.List;

public interface MedicalRecordService {

    MedicalRecordDto addMedicalRecord(int patientId, MedicalRecordDto dto);

    List<MedicalRecordDto> getRecordsByPatientId(int patientId);

    MedicalRecordDto addMedicineToRecord(Long recordId, List<MedicineDto> medicines);
}

