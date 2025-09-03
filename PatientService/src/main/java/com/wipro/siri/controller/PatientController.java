package com.wipro.siri.controller;

import com.wipro.siri.entities.MedicalRecord;
import com.wipro.siri.entities.Medicine;
import com.wipro.siri.entities.Patient;
import com.wipro.siri.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // ===================== Patient CRUD =====================

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== Medical Records =====================

    @PostMapping("/{patientId}/medical-records")
    public ResponseEntity<Patient> addMedicalRecord(@PathVariable Long patientId,
                                                    @RequestBody MedicalRecord record) {
        Optional<Patient> patientOpt = patientService.getPatientById(patientId);
        if (patientOpt.isEmpty()) return ResponseEntity.notFound().build();

        Patient patient = patientOpt.get();
        record.setPatient(patient);

        if (patient.getMedicalRecords() != null) {
            patient.getMedicalRecords().add(record);
        }

        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    // ===================== Medicines =====================

    @PostMapping("/{patientId}/medical-records/{recordId}/medicines")
    public ResponseEntity<Patient> addMedicine(@PathVariable Long patientId,
                                               @PathVariable Long recordId,
                                               @RequestBody Medicine medicine) {
        Optional<Patient> patientOpt = patientService.getPatientById(patientId);
        if (patientOpt.isEmpty()) return ResponseEntity.notFound().build();

        Patient patient = patientOpt.get();

        MedicalRecord record = patient.getMedicalRecords().stream()
                .filter(r -> r.getRecordId().equals(recordId))
                .findFirst()
                .orElse(null);

        if (record == null) return ResponseEntity.notFound().build();

        medicine.setMedicalRecord(record);

        if (record.getMedicines() != null) {
            record.getMedicines().add(medicine);
        }

        return ResponseEntity.ok(patientService.savePatient(patient));
    }
}
