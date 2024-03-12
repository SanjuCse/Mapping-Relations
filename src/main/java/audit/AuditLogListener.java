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
public class AuditLogListener /*implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener */{

//	@Autowired
//	private AuditLogRepository auditLogRepository;
//
//	@Override
//	public void onPostInsert(PostInsertEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = createAuditLog(entity, "INSERT");
//			auditLogRepository.save(auditLog);
//		}
//	}
//
//	@Override
//	public void onPostUpdate(PostUpdateEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = createAuditLog(entity, "UPDATE");
//			auditLogRepository.save(auditLog);
//		}
//	}
//
//	@Override
//	public void onPostDelete(PostDeleteEvent event) {
//		Object entity = event.getEntity();
//		if (!(entity instanceof AuditLog)) {
//			AuditLog auditLog = createAuditLog(entity, "DELETE");
//			auditLogRepository.save(auditLog);
//		}
//	}
//
//	private AuditLog createAuditLog(Object entity, String action) {
//		AuditLog auditLog = new AuditLog();
//		auditLog.setEntityName(entity.getClass().getName());
//		auditLog.setAction(action);
////	        auditLog.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		auditLog.setUsername(null);
//		auditLog.setTimestamp(LocalDateTime.now());
//		auditLog.setEntityId(String.valueOf(event.getId()));
//
//		// For UPDATE, set oldValue and newValue for each modified field
//		if ("UPDATE".equals(action)) {
//			Object[] oldState = event.getOldState();
//			Object[] newState = event.getState();
//
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < oldState.length; i++) {
//				if (oldState[i] != null && newState[i] != null && !oldState[i].equals(newState[i])) {
//					if (sb.length() > 0) {
//						sb.append(", ");
//					}
//					sb.append(event.getPersister().getPropertyNames()[i]).append(": ").append(oldState[i])
//							.append(" -> ").append(newState[i]);
//				}
//			}
//			auditLog.setEntityColumnName(sb.toString());
//		}
//
//		return auditLog;
//	}
//
//	@Override
//	public boolean requiresPostCommitHandling(EntityPersister persister) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}