package com.sanju.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "AUDIT_LOG")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_DETAILS_ID")
	private Long auditDetailsId;

	@Column(name = "TABLE_NAME", length = 50)
	private String tableName;

	@Column(name = "PK_ID")
	private Long pkId;

	@Column(name = "JSON_VALUE", columnDefinition = "text")
	private String jsonValue;

	@Column(name = "ACTION", length = 10)
	private String action;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "TIMESTAMP")
	private LocalDateTime timestamp;
}