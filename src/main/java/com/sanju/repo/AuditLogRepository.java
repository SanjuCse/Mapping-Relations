package com.sanju.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanju.model.AuditLog;

import jakarta.transaction.Transactional;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM AuditLog al WHERE al.auditDetailsId = "
			+ "(SELECT ad.id FROM (SELECT ad1.auditDetailsId AS id FROM AuditLog ad1 WHERE ad1.pkId = :pkId AND ad1.action = :action ORDER BY ad1.auditDetailsId DESC LIMIT 1) AS ad)")
	int deleteSecondLastRecord(@Param("pkId") Long pkId, @Param("action") String action);
}
