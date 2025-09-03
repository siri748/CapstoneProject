package com.wipro.siri.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.wipro.siri.dto.PatientDto;

@FeignClient(name = "patient-service", url = "http://localhost:8082")
public interface PatientClient {

    @GetMapping("/api/patients/{id}")  // include the /api prefix
    PatientDto getPatientById(@PathVariable("id") Long id);
}
