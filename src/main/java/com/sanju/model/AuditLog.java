package com.sanju.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "AUDIT_LOG")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_DETAILS_ID")
	private Long id;

	@Column(name = "TABLE_NAME", length = 50)
	private String tableName;
	
	@Column(name = "COLUMN_NAME", length = 100)
	private String columnName;

	@Lob
	@Column(name = "ENTITY_ID", length = 5)
	private String entityId;

//	@Lob
//	private String entityData;

	@Column(name = "OLD_VALUE", columnDefinition = "text")
	private String oldValue;

	@Column(name = "NEW_VALUE", columnDefinition = "text")
	private String newValue;

	@Column(name = "ACTION", length = 10)
	private String action;

	@Column(name = "USERNAME", length = 20)
	private String username;

	@Column(name = "TIMESTAMP")
	private LocalDateTime timestamp;
}