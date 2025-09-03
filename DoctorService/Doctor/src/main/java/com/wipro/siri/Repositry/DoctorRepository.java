package com.wipro.siri.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.siri.Entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findBySpecialization(String specialization);

	

}
