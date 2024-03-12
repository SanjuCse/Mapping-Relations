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
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "entity_name", length = 50)
	private String entityName;

	@Lob
	@Column(name = "entity_id", length = 5)
	private String entityId;

//	@Lob
//	private String entityData;

	@Column(name = "old_value", length = 20)
	private String oldValue;

	@Column(name = "new_value", length = 20)
	private String newValue;

	@Column(name = "action", length = 10)
	private String action;

	@Column(name = "username", length = 20)
	private String username;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;
}