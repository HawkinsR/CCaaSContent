package com.example.demoapp;

import com.example.demoapp.model.User;
import com.example.demoapp.util.HibernateLocator;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DirtyCheckDemo {

    public static void main(String[] args) {
        // Setup a test user first (assume the DB is blank)
        setupUser();

        System.out.println("--- Starting Dirty Checking Demo ---");
        Session session = HibernateLocator.openSession();
        Transaction tx = session.beginTransaction();

        // 1. First-Level Cache Demo
        System.out.println("Fetching user for the first time:");
        User u1 = session.get(User.class, 1L); // Hits DB

        System.out.println("Fetching user for the second time:");
        User u2 = session.get(User.class, 1L); // Hits Cache

        System.out.println("Are they the exact same Java instance? " + (u1 == u2));

        // 2. Dirty Checking Demo
        System.out.println("\nModifying user email...");
        u1.setEmail("new.email@demo.com");

        System.out.println("Committing transaction... Watch for the UPDATE statement!");
        // We DID NOT call session.update() or session.persist()
        tx.commit();

        session.close();
        HibernateLocator.getSessionFactory().close();
    }

    private static void setupUser() {
        try (Session s = HibernateLocator.openSession()) {
            Transaction tx = s.beginTransaction();
            User testUser = new User();
            testUser.setName("Tester");
            testUser.setEmail("old@demo.com");
            s.persist(testUser);
            tx.commit();
        }
    }
}
