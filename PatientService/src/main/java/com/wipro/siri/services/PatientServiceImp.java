package com.wipro.siri.services;

import com.wipro.siri.config.EventPublisher;
import com.wipro.siri.dto.EventMessage;
import com.wipro.siri.entities.Patient;
import com.wipro.siri.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepo patientRepo;
    private final EventPublisher eventPublisher;

    @Autowired
    public PatientServiceImp(PatientRepo patientRepo, EventPublisher eventPublisher) {
        this.patientRepo = patientRepo;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Patient savePatient(Patient patient) {
        // Save the patient to DB
        Patient savedPatient = patientRepo.save(patient);

        // ✅ Publish event to Kafka after saving
        EventMessage event = new EventMessage(
                "PATIENT_REGISTERED",           // Event type
                "patient",                      // Entity type
                String.valueOf(savedPatient.getPatientId()), // ✅ Correct getter
                "New patient registered successfully"
        );
        eventPublisher.publishPatientEvent(event);

        return savedPatient;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepo.findById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public void deletePatient(Long id) {
        patientRepo.deleteById(id);
    }
}
