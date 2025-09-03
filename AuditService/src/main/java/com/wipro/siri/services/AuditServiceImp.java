package com.wipro.siri.services;

import org.springframework.stereotype.Service;

import com.wipro.siri.entities.AuditLog;
import com.wipro.siri.repo.AuditRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditServiceImp implements AuditService {

    private final AuditRepo auditRepo;

    public AuditServiceImp(AuditRepo auditRepo) {
        this.auditRepo = auditRepo;
    }

    @Override
    public AuditLog saveAuditLog(String eventType, String entityType, String entityId, String description) {
        AuditLog log = new AuditLog();
        log.setEventType(eventType);
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setDescription(description);
        log.setTimestamp(LocalDateTime.now());
        return auditRepo.save(log);
    }

    @Override
    public List<AuditLog> getAllLogs() {
        return auditRepo.findAll();
    }

    @Override
    public List<AuditLog> getLogsByEntityType(String entityType) {
        return auditRepo.findByEntityType(entityType);
    }

    @Override
    public List<AuditLog> getLogsByEventType(String eventType) {
        return auditRepo.findByEventType(eventType);
    }
}
