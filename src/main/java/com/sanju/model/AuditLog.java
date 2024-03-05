package com.sanju.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

//@ToString
//public class AuditLog {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String entityName;
//
//	@Lob
//	private String entityData;
//}
@Entity
@Setter
@Getter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;

    @Lob
    private String entityId;

    @Lob
    private String entityData;

    private String action;

    private String username;

    private LocalDateTime timestamp;
}