# Exercise: JMS Messaging Basics

## Overview

**Mode:** Implementation (Code Lab)
**Time Estimate:** 1.5 Hours

**Scenario:** You are building an email notification system. When a user registers on the website, we don't want the user to wait while the email sends. Instead, we want to drop a message onto a queue and let a background worker process it asynchronously.

## Your Goal

Configure an embedded JMS connection in Spring Boot, send a registration message to a queue from a REST controller, and write a listener to consume and log it.

---

## Starter Code Setup

Navigate to the `/starter_code` directory. The `spring-boot-starter-activemq` dependency is already in your `pom.xml`.

---

## Core Tasks

### Task 1: Broker Configuration

1. Open `application.properties`.
2. Configure an in-memory ActiveMQ broker. (Refer to your notes or the demo!).
3. Add the `@EnableJms` annotation to your main application class.

### Task 2: The Producer

1. Create an `EmailProducer` service.
2. Inject `JmsTemplate`.
3. Write a method `sendWelcomeEmail(String emailAddress)` that sends the string to a queue named `"welcome-email-queue"`.

### Task 3: The Consumer

1. Create an `EmailWorker` component.
2. Write a method annotated with `@JmsListener` that listens to `"welcome-email-queue"`.
3. The method should simulate work by sleeping the thread for 2 seconds (`Thread.sleep(2000)`), and then print: `"Simulated sending welcome email to: [emailAddress]"`.

### Task 4: The Trigger

1. Create a `RegistrationController`.
2. Create a `POST /api/register` endpoint that takes an email address as a request parameter (`?email=user@example.com`).
3. Have the endpoint call your `EmailProducer` and immediately return the string `"Registration received. Email is processing."`

---

## Definition of Done

- When you hit the REST endpoint via browser or Postman, the HTTP response returns *instantly*.
- Two seconds *after* the HTTP response returns, the console prints the simulated email sent message. This proves the work was handed off asynchronously.
