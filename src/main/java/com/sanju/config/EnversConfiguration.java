package com.sanju.config;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class EnversConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private AuditLogListener auditLogListener;

//    @PostConstruct
//    protected void init() {
//        EventListenerRegistry registry = entityManagerFactory.unwrap(SessionFactory.class).getServiceRegistry().getService(EventListenerRegistry.class);
//        registry.appendListeners(EventType.POST_INSERT, auditLogListener);
//    }
    
//    @PostConstruct
//    protected void init() {
//        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
//        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
//        registry.appendListeners(EventType.POST_INSERT, auditLogListener);
//    }
    @PostConstruct
    protected void init() {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.POST_INSERT, auditLogListener);
        registry.appendListeners(EventType.POST_UPDATE, auditLogListener);
    }
}