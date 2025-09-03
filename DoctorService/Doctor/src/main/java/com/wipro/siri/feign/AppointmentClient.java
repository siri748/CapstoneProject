package com.wipro.siri.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.siri.dto.AppointmentDto;

@FeignClient(name = "appointment-service", url = "http://localhost:8085")
public interface AppointmentClient {

    // Get all appointments for a doctor
    @GetMapping("/api/appointments/doctor/{doctorId}")
    List<AppointmentDto> getAppointmentsByDoctor(@PathVariable("doctorId") Long doctorId);
}
