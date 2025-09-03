package com.wipro.siri.services;

import com.wipro.siri.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient savePatient(Patient patient);

    Optional<Patient> getPatientById(Long id);

    List<Patient> getAllPatients();

    void deletePatient(Long id);
}
