package audit;

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

//@Component
public class AuditLogListener1 implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	@Autowired
	private AuditLogRepository auditLogRepository;

//	@Override
//	public void onPostInsert(PostInsertEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = new AuditLog();
//			auditLog.setEntityName(entity.getClass().getName());
//			auditLog.setAction("INSERT");
//
////          String username = SecurityContextHolder.getContext().getAuthentication().getName();
//			String username = null;
//			auditLog.setUsername(username);
//
//			LocalDateTime timestamp = LocalDateTime.now();
//			auditLog.setTimestamp(timestamp);
//
//			auditLog.setEntityId(event.getId().toString());
//
//			Object[] state = event.getState();
//			StringBuilder entityDataBuilder = new StringBuilder();
//			for (Object value : state) {
//				if (entityDataBuilder.length() > 0) {
//					entityDataBuilder.append(", ");
//				}
//				entityDataBuilder.append(value);
//			}
//			auditLog.setEntityData(entityDataBuilder.toString());
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
//			auditLog.setEntityName(entity.getClass().getName());
//			auditLog.setAction("UPDATE");
//
//			String username = null;
//			auditLog.setUsername(username);
//
//			LocalDateTime timestamp = LocalDateTime.now();
//			auditLog.setTimestamp(timestamp);
//
//			auditLog.setEntityId(event.getId().toString());
//
//			Object[] newState = event.getState();
//			StringBuilder entityDataBuilder = new StringBuilder();
//			for (Object value : newState) {
//				if (entityDataBuilder.length() > 0) {
//					entityDataBuilder.append(", ");
//				}
//				entityDataBuilder.append(value);
//			}
//			auditLog.setEntityData(entityDataBuilder.toString());
//
//			auditLogRepository.save(auditLog);
//		}
//	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			AuditLog auditLog = new AuditLog();
			auditLog.setEntityName(entity.getClass().getName());
			auditLog.setAction("INSERT");

//			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			String username = null;
			auditLog.setUsername(username);

			LocalDateTime timestamp = LocalDateTime.now();
			auditLog.setTimestamp(timestamp);
			
			auditLog.setEntityId(event.getId().toString());

			auditLog.setNewValue(getEntityData(entity));

			auditLogRepository.save(auditLog);
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			AuditLog auditLog = new AuditLog();
			auditLog.setEntityName(entity.getClass().getName());
			auditLog.setAction("UPDATE");

//			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			String username = null;
			auditLog.setUsername(username);

			LocalDateTime timestamp = LocalDateTime.now();
			auditLog.setTimestamp(timestamp);
			
			auditLog.setEntityId(event.getId().toString());

			auditLog.setOldValue(getEntityData(event.getOldState()));
			auditLog.setNewValue(getEntityData(entity));

			auditLogRepository.save(auditLog);
		}
	}


	@Override
	public void onPostDelete(PostDeleteEvent event) {
		Object entity = event.getEntity();
		if (!(entity instanceof AuditLog)) {
			AuditLog auditLog = new AuditLog();
			auditLog.setEntityName(entity.getClass().getName());
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
}