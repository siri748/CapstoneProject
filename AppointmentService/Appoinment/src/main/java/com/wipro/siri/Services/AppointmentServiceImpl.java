package com.wipro.siri.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.siri.Entities.Appointment;
import com.wipro.siri.Reposirty.AppointmentRepository;
import com.wipro.siri.feign.PatientClient;
import com.wipro.siri.feign.DoctorClient;
import com.wipro.siri.dto.PatientDto;
import com.wipro.siri.dto.DoctorDto;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientClient patientClient;
    private final DoctorClient doctorClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientClient patientClient,
                                  DoctorClient doctorClient) {
        this.appointmentRepository = appointmentRepository;
        this.patientClient = patientClient;
        this.doctorClient = doctorClient;
    }

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus(Appointment.Status.BOOKED);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(app -> {
            app.setAppointmentTime(updatedAppointment.getAppointmentTime());
            app.setNotes(updatedAppointment.getNotes());
            app.setDoctorId(updatedAppointment.getDoctorId());
            app.setPatientId(updatedAppointment.getPatientId());
            return appointmentRepository.save(app);
        }).orElse(null);
    }

    @Override
    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment updateStatus(Long id, Appointment.Status status) {
        return appointmentRepository.findById(id).map(app -> {
            app.setStatus(status);
            return appointmentRepository.save(app);
        }).orElse(null);
    }

    // Fetch patient details for an appointment
    @Override
    public PatientDto getPatientDetailsForAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .map(app -> patientClient.getPatientById(app.getPatientId()))
                .orElse(null);
    }

    // Fetch doctor details for an appointment
    @Override
    public DoctorDto getDoctorDetailsForAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .map(app -> doctorClient.getDoctorById(app.getDoctorId()))
                .orElse(null);
    }
}
