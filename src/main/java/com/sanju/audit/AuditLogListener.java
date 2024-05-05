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

import com.sanju.model.AuditLog;
import com.sanju.repo.AuditLogRepository;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	@Autowired
	private AuditLogRepository auditLogRepository;


	@Override
	public void onPostInsert(PostInsertEvent event) {
	    Object entity = event.getEntity();
	    if (!(entity instanceof AuditLog)) {
	        String tableName = getTableName(entity.getClass());
	        String entityId = event.getId().toString();
	        String action = "INSERT";
	        String username = null;
	        LocalDateTime timestamp = LocalDateTime.now();

	        saveFieldChanges(entity, null, tableName, entityId, action, username, timestamp);
	    }
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
	    Object entity = event.getEntity();
	    if (!(entity instanceof AuditLog)) {
	        String tableName = getTableName(entity.getClass());
	        String entityId = event.getId().toString();
	        String action = "UPDATE";
	        String username = null;
	        LocalDateTime timestamp = LocalDateTime.now();

	        Object[] oldState = event.getOldState();
//	        Object[] newState = event.getState();

	        saveFieldChanges(entity, oldState, tableName, entityId, action, username, timestamp);
	    }
	}
	
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		Object entity = event.getEntity();
		Object[] deletedState = event.getDeletedState();
		if (!(entity instanceof AuditLog)) {
			String tableName = getTableName(entity.getClass());
			String entityId = event.getId().toString();
			String action = "DELETE";
			String username = null;
			LocalDateTime timestamp = LocalDateTime.now();

			try {
				saveDeleteRecord(tableName, entityId, action, username, timestamp, deletedState);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveDeleteRecord(String tableName, String entityId, String action, String username, LocalDateTime timestamp, Object[] deletedState) throws IllegalArgumentException, IllegalAccessException {
		AuditLog auditLog = new AuditLog();
		auditLog.setTableName(tableName);
		auditLog.setAction(action);
		auditLog.setUsername(username);
		auditLog.setTimestamp(timestamp);
		auditLog.setEntityId(entityId);
		
//		StringBuilder deletedStateBuilder = new StringBuilder();
//	    if (deletedState != null) {
//	        for (int i = 0; i < deletedState.length; i++) {
//	            if (i > 0) {
//	                deletedStateBuilder.append(", ");
//	            }
//	            deletedStateBuilder.append(deletedState[i]);
//	        }
//	    }
//	    auditLog.setOldValue(deletedStateBuilder.toString());

		auditLogRepository.save(auditLog);
	}
	
	private void saveFieldChanges(Object entity, Object[] oldState, String tableName, String entityId, String action,
			String username, LocalDateTime timestamp) {
		try {
			Class<?> clazz = entity.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object oldValue = null;
				Object newValue = field.get(entity);

				if (oldState != null && oldState.length != 0) {
					for (Object obj : oldState) {
						Class<?> clazzOs = obj.getClass();
						Field[] fieldsOs = clazzOs.getDeclaredFields();
						for (Field fieldOs : fieldsOs) {
							if (fieldOs.getType() == byte[].class) {
								continue;
							}
							fieldOs.setAccessible(true);
							String fieldNameOs = fieldOs.getName();
							if (fieldName.equalsIgnoreCase(fieldNameOs)) {
								oldValue = fieldOs.get(obj);
								break;
							}
						}
					}
				}

				if ((oldValue == null && newValue == null) || (oldValue != null && oldValue.equals(newValue))) {
					continue;
				}

				AuditLog auditLog = new AuditLog();
				auditLog.setTableName(tableName);
				auditLog.setAction(action);
				auditLog.setUsername(username);
				auditLog.setTimestamp(timestamp);
				auditLog.setEntityId(entityId);
				auditLog.setColumnName(getColumnName(field));
				auditLog.setOldValue((oldValue != null) ? oldValue.toString() : null);
				auditLog.setNewValue((newValue != null) ? newValue.toString() : null);

				auditLogRepository.save(auditLog);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static String getTableName(Class<?> entityClass) {
		Table tableAnnotation = entityClass.getAnnotation(Table.class);
		if (tableAnnotation != null) {
			String tableName = tableAnnotation.name();
			if (!tableName.isEmpty()) {
				return tableName.toUpperCase();
			}
		}
		return null;
	}
	
	public static String getColumnName(Field field) {
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
	
	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		return true;
	}
	
//	private String getEntityData(Object entity) {
//		return entity.toString();
//	}
}