package com.wipro.siri.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.wipro.siri.Entities.Doctor;
import com.wipro.siri.Repositry.DoctorRepository;
import com.wipro.siri.dto.PatientDto;
import com.wipro.siri.dto.AppointmentDto;
import com.wipro.siri.feign.PatientClient;
import com.wipro.siri.feign.AppointmentClient;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientClient patientClient;
    private final AppointmentClient appointmentClient;

    public DoctorServiceImpl(DoctorRepository doctorRepository, 
                             PatientClient patientClient,
                             AppointmentClient appointmentClient) {
        this.doctorRepository = doctorRepository;
        this.patientClient = patientClient;
        this.appointmentClient = appointmentClient;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setEmail(updatedDoctor.getEmail());
            existingDoctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
            existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
            existingDoctor.setExperienceYears(updatedDoctor.getExperienceYears());
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public PatientDto getPatientDetails(Long patientId) {
        return patientClient.getPatientById(patientId);
    }

    @Override
    public List<AppointmentDto> getAppointmentsForDoctor(Long doctorId) {
        return appointmentClient.getAppointmentsByDoctor(doctorId);
    }
}
