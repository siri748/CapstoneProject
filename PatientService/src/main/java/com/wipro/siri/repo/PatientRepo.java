package com.wipro.siri.repo;

import com.wipro.siri.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    // JpaRepository already provides:
    // save(), findById(), findAll(), deleteById(), etc.
    // No need to declare custom deleteById
}
