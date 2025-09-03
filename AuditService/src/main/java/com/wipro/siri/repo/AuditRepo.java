package com.wipro.siri.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.siri.entities.AuditLog;

import java.util.List;

@Repository
public interface AuditRepo extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityType(String entityType);
    List<AuditLog> findByEventType(String eventType);
}
