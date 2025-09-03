package com.wipro.siri.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.siri.entities.AuditLog;
import com.wipro.siri.services.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(auditService.getAllLogs());
    }

    @GetMapping("/entity")
    public ResponseEntity<List<AuditLog>> getLogsByEntityType(@RequestParam String entityType) {
        return ResponseEntity.ok(auditService.getLogsByEntityType(entityType));
    }

    @GetMapping("/event")
    public ResponseEntity<List<AuditLog>> getLogsByEventType(@RequestParam String eventType) {
        return ResponseEntity.ok(auditService.getLogsByEventType(eventType));
    }
}

