package com.sanju.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENTERPRISE_AUDIT")
public class EnterpriseAudit {

	@Id
	@Column(name = "EA_ID", nullable = false)
	private Long eaId;

	@Column(name = "EA_CARRIED_DATE")
	private Timestamp carriedDate;

	@Column(name = "EA_CARRIED_TIME", length = 40)
	private String carriedTime;

	@Column(name = "EA_LOCAL_TIMEZONE", length = 40)
	private String localTimezone;

	@Column(name = "EA_CARRIED_BY")
	private Long carriedBy;

	@Column(name = "EA_USER_NAME", length = 200)
	private String userName;

	@Column(name = "EA_EPM_ID")
	private Long epmId;

	@Column(name = "EA_PROJECT_NAME", length = 50)
	private String projectName;

	@Column(name = "EA_MODULE_NAME", length = 400)
	private String moduleName;

	@Column(name = "EA_SUB_MODULE", length = 400)
	private String subModule;

	@Column(name = "EA_PAGE_NAME", length = 400)
	private String pageName;

	@Column(name = "EA_SUB_PAGE", length = 400)
	private String subPage;

	@Column(name = "EA_ACTION", length = 200)
	private String action;

	@Column(name = "EA_TABLE_NAME", length = 400)
	private String tableName;

	@Column(name = "EA_FIELD_NAME", length = 400)
	private String fieldName;

	@Column(name = "EA_OLD_VALUE", length = 400)
	private String oldValue;

	@Column(name = "EA_NEW_VALUE", length = 400)
	private String newValue;

	@Column(name = "EA_CRM_ID")
	private Long crmId;

	@Column(name = "EA_CMP_ID")
	private Long cmpId;
}
