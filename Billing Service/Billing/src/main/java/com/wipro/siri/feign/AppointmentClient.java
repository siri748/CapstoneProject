package com.wipro.siri.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.siri.dto.AppointmentDto;

@FeignClient(name = "appointment-service", url = "http://localhost:8085")
public interface AppointmentClient {
    @GetMapping("/api/appointments/{id}")
    AppointmentDto getAppointmentById(@PathVariable("id") Long id);
}

