package com.wipro.siri.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.siri.Entities.Appointment;
import com.wipro.siri.Services.AppointmentService;
import com.wipro.siri.dto.PatientDto;
import com.wipro.siri.dto.DoctorDto;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @PatchMapping("/{id}/status/{status}")
    public Appointment updateStatus(@PathVariable Long id, @PathVariable Appointment.Status status) {
        return appointmentService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
    }

    // Fetch Patient Details for a specific appointment
    @GetMapping("/{appointmentId}/patient")
    public PatientDto getPatientForAppointment(@PathVariable Long appointmentId) {
        return appointmentService.getPatientDetailsForAppointment(appointmentId);
    }

    // Fetch Doctor Details for a specific appointment
    @GetMapping("/{appointmentId}/doctor")
    public DoctorDto getDoctorForAppointment(@PathVariable Long appointmentId) {
        return appointmentService.getDoctorDetailsForAppointment(appointmentId);
    }
}
