package com.sanju.audit;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanju.model.AuditLog;
import com.sanju.repo.AuditLogRepository;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	@Autowired
	private AuditLogRepository auditLogRepository;
	
	public static final String INSERT = "INSERT";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			String tableName = getTableName(entity.getClass());
			String entityId = event.getId().toString();
			String action = INSERT;
			Long userId = null; // TODO: Need to get from Security Context Holder
			LocalDateTime timestamp = LocalDateTime.now();

			saveFieldChanges(entity, tableName, entityId, action, userId, timestamp);
//			int deleteCount = auditLogRepository.deleteSecondLastRecord(Long.valueOf(entityId), INSERT);
//			log.info(deleteCount+" records deleted");
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			String tableName = getTableName(entity.getClass());
			String entityId = event.getId().toString();
			String action = UPDATE;
			Long userId = null; // TODO: Need to get from Security Context Holder
			LocalDateTime timestamp = LocalDateTime.now();

			saveFieldChanges(entity, tableName, entityId, action, userId, timestamp);
		}
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		Object entity = event.getEntity();
		Object[] deletedState = event.getDeletedState();
		if (!(entity instanceof AuditLog)) {
			String tableName = getTableName(entity.getClass());
			String entityId = event.getId().toString();
			String action = DELETE;
			Long userId = null; // TODO: Need to get from Security Context Holder
			LocalDateTime timestamp = LocalDateTime.now();

			saveDeleteRecord(tableName, entityId, action, userId, timestamp, deletedState);
		}
	}

	private void saveDeleteRecord(String tableName, String entityId, String action, Long userId,
			LocalDateTime timestamp, Object[] deletedState) {
		AuditLog auditLog = new AuditLog();
		auditLog.setTableName(tableName);
		auditLog.setAction(action);
		auditLog.setUserId(userId);
		auditLog.setTimestamp(timestamp);
		auditLog.setPkId(Long.valueOf(entityId));

		auditLogRepository.save(auditLog);
	}

	private void saveFieldChanges(Object obj, String tableName, String pkId, String action, Long userId, LocalDateTime timestamp){
		if (obj != null) {
			AuditLog auditLog = new AuditLog();
			auditLog.setTableName(tableName);
			auditLog.setAction(action);
			auditLog.setUserId(userId);
			auditLog.setTimestamp(timestamp);
			auditLog.setPkId(Long.valueOf(pkId));
			auditLog.setJsonValue(getJsonData(obj));

			auditLogRepository.save(auditLog);
		}
	}


	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		return true;
	}
	
	private static String getTableName(Class<?> entityClass) {
		Table tableAnnotation = entityClass.getAnnotation(Table.class);
		if (tableAnnotation != null) {
			String tableName = tableAnnotation.name();
			if (!tableName.isEmpty()) {
				return tableName.toUpperCase();
			}
		}
		return null;
	}
	
	private static String getColumnName(Field field) {
		String columnName = null;
		
		Column columnAnnotation = field.getAnnotation(Column.class);
		if (columnAnnotation != null && !columnAnnotation.name().isEmpty()) {
			columnName = columnAnnotation.name().toUpperCase();
		} else {
			JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);
			if (joinColumnAnnotation != null && !joinColumnAnnotation.name().isEmpty()) {
				columnName = joinColumnAnnotation.name().toUpperCase();
			}
		}
		
		return columnName;
	}

	private String getJsonData(Object obj) {
		String jsonValue = null;
		try {
			jsonValue = new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
		}
		return jsonValue;
	}
}