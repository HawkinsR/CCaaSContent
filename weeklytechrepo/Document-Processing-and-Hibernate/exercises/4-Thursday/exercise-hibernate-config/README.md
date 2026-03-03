# Exercise: Hibernate Configuration & Setup (Collaborative Pair Programming)

## Overview

**Mode:** Collaborative Pair Programming (Driver/Navigator)
**Time Estimate:** 1.5 - 2 Hours

**The Thursday Protocol:** For today's activities, you will work in pairs. One person is the **Driver** (typing the code), and one is the **Navigator** (reviewing the logic and architecture). Switch roles every 30 minutes!

**Scenario:** The year is 2012. You have inherited a monolithic legacy application that does not use Spring Boot. You need to configure raw Hibernate using XML and establish a connection to the database.

## Your Goal

Configure `hibernate.cfg.xml` from scratch, map a multi-entity schema using the Service Locator pattern, and perform basic CRUD operations using raw Hibernate Sessions.

---

## Starter Code Setup

Navigate to `/starter_code`. This is a plain Maven project. There is NO Spring Boot here. We supplied the raw `hibernate-core` dependency.

---

## Core Tasks

### Task 1: The Configuration (Navigator leads, Driver types)

1. In `src/main/resources`, create `hibernate.cfg.xml`.
2. Configure a connection to an H2 in-memory database.
3. Set the dialect to `org.hibernate.dialect.H2Dialect`.
4. Turn on `show_sql` and set `hbm2ddl.auto` to `create-drop`.

### Task 2: The Entities

1. Create a `Student` and a `Course` entity.
2. Set up a `@ManyToMany` relationship between them.
3. Don't forget! You *must* register these entity classes using `<mapping class="..."/>` tags at the bottom of your `hibernate.cfg.xml`. In vanilla Hibernate, package scanning isn't automatic!

### Task 3: The Service Locator

1. Build a `HibernateUtil` class.
2. Use a static block to instantiate the `Configuration`, read the XML file, and build the `SessionFactory` singleton.
3. Expose a `public static SessionFactory getSessionFactory()` method.

### Task 4: The Manual Transaction Lifecycle (Switch Roles!)

1. In the `Main.java` class, write code to persist two courses and one student enrolled in both courses.
2. You must manually execute the unit of work lifecycle:

   ```java
   Session session = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = session.beginTransaction();
   try {
       // Persist your data
       tx.commit();
   } catch (Exception e) {
       tx.rollback();
   } finally {
       session.close();
   }
   ```

---

## Definition of Done

1. The application successfully boots and does NOT fail during SessionFactory creation.
2. Console output shows the auto-generated join table for the ManyToMany relationship.
3. The data saves successfully and the connection pool closes cleanly at the end of `main()`.
