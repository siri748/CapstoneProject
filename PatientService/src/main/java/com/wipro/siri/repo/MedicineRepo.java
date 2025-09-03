package com.wipro.siri.repo;

import com.wipro.siri.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine, Long> {
}
