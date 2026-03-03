package com.example.hibernate;

import com.example.hibernate.model.Product;
import com.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
        // --- 1. Persist a new object ---
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Product p = new Product("Ergonomic Keyboard", 95.0);
            session.persist(p);

            tx.commit(); // Translates to SQL INSERT
            System.out.println("Product saved with ID: " + p.getId());
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close(); // Return connection to pool
        }

        // --- 2. Retrieve the object ---
        Session newSession = HibernateUtil.getSessionFactory().openSession();
        try {
            // No transaction strictly needed for simple read operations
            Product fetchedProfile = newSession.get(Product.class, 1L);
            System.out.println("Fetched: " + fetchedProfile.getName());
        } finally {
            newSession.close();
        }

        // Clean shutdown
        HibernateUtil.shutdown();
    }
}
