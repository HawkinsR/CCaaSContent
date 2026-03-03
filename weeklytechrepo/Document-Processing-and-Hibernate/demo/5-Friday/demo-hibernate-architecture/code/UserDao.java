package com.example.demoapp.dao;

import com.example.demoapp.model.User;
import com.example.demoapp.util.HibernateLocator;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data Access Object that depends on the Service Locator
 * to obtain its database sessions.
 */
public class UserDao {

    /**
     * Retrieves a user by ID. Does NOT require a transaction for a simple read.
     */
    public User findById(Long id) {
        // Obtains session via Service Locator
        try (Session session = HibernateLocator.openSession()) {
            return session.get(User.class, id);
        }
    }

    /**
     * Saves a user. Requires explicit transaction management.
     */
    public void save(User user) {
        Session session = HibernateLocator.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
