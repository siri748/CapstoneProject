package com.wipro.siri.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.siri.dto.PatientDto;

@FeignClient(name = "patient-service", url = "http://localhost:8082") // URL of Patient Service
public interface PatientClient {

    @GetMapping("/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);
}

