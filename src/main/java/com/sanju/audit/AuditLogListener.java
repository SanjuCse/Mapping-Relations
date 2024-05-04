package com.sanju.audit;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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

import jakarta.persistence.Table;

@Component
public class AuditLogListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	@Autowired
	private AuditLogRepository auditLogRepository;

//	@Override
//	public void onPostInsert(PostInsertEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = new AuditLog();
//			auditLog.setTableName(getTableName(entity.getClass()));
//			auditLog.setAction("INSERT");
//
////			String username = SecurityContextHolder.getContext().getAuthentication().getName();
//			String username = null;
//			auditLog.setUsername(username);
//
//			LocalDateTime timestamp = LocalDateTime.now();
//			auditLog.setTimestamp(timestamp);
//			auditLog.setEntityId(event.getId().toString());
//			auditLog.setNewValue(getEntityData(entity));
//
//			auditLogRepository.save(auditLog);
//		}
//	}
//
//	@Override
//	public void onPostUpdate(PostUpdateEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = new AuditLog();
//			auditLog.setTableName(getTableName(entity.getClass()));
//			auditLog.setAction("UPDATE");
//
////			String username = SecurityContextHolder.getContext().getAuthentication().getName();
//			String username = null;
//			auditLog.setUsername(username);
//
//			LocalDateTime timestamp = LocalDateTime.now();
//			auditLog.setTimestamp(timestamp);
//			auditLog.setEntityId(event.getId().toString());
//			Object[] oldState = event.getOldState();
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < oldState.length; i++) {
//				if (oldState[i] != null) {
//					if (sb.length() > 0) {
//						sb.append(", ");
//					}
//					sb.append(event.getPersister().getPropertyNames()[i]).append(": ").append(oldState[i]);
//				}
//			}
//			auditLog.setOldValue(getEntityData(sb));
//			auditLog.setNewValue(getEntityData(entity));
//
//			auditLogRepository.save(auditLog);
//		}
//	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			AuditLog auditLog = new AuditLog();
			auditLog.setTableName((getTableName(entity.getClass())));
			auditLog.setAction("DELETE");

			String username = null;
			auditLog.setUsername(username);

			LocalDateTime timestamp = LocalDateTime.now();
			auditLog.setTimestamp(timestamp);
			auditLog.setEntityId(event.getId().toString());

			auditLogRepository.save(auditLog);
		}
	}

	private String getEntityData(Object entity) {
		return entity.toString();
	}

	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		return true;
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
	        Object[] newState = event.getState();

	        saveFieldChanges(entity, oldState, tableName, entityId, action, username, timestamp);
	    }
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
							fieldOs.setAccessible(true);
							String fieldNameOs = fieldOs.getName();
							if (fieldName.equalsIgnoreCase(fieldNameOs)) {
								oldValue = fieldOs.get(obj);
								break;
							}
						}
					}
				}
				
				if (oldValue != null) {
					if ((oldValue == null && newValue == null) || (oldValue.equals(newValue))) {
						continue; // Skip if both old and new values are null or same
					}
				}

				AuditLog auditLog = new AuditLog();
				auditLog.setTableName(tableName);
				auditLog.setAction(action);
				auditLog.setUsername(username);
				auditLog.setTimestamp(timestamp);
				auditLog.setEntityId(entityId);
				auditLog.setColumnName(fieldName);
				auditLog.setOldValue((oldValue != null) ? oldValue.toString() : null);
				auditLog.setNewValue((newValue != null) ? newValue.toString() : null);

				auditLogRepository.save(auditLog);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}