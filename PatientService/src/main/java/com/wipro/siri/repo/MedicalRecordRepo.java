package com.wipro.siri.repo;

import com.wipro.siri.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepo extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatient_PatientId(Long patientId);
}
