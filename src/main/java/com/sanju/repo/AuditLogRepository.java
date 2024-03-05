package com.sanju.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanju.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
