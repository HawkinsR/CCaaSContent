package com.example.demoapp.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Service Locator Pattern implementation.
 * Provides a global point of access to the singleton SessionFactory.
 */
public class HibernateLocator {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * @return The single, application-wide SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Convenience method to open a new session.
     */
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
