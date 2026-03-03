# Exercise: Full JMS Client Application

## Overview

**Mode:** Implementation (Code Lab)
**Time Estimate:** 1.5 - 2 Hours

**Scenario:** You need a standalone worker application that can process complex objects via JMS.

## Your Goal

Build a producer/consumer JMS application that exchanges serialized `Order` objects and filters them based on message priority properties using Message Selectors.

---

## Starter Code Setup

Navigate to the `/starter_code` directory. The basic Spring Boot structure is created.

---

## Core Tasks

### Task 1: The Model

1. Create an `Order` POJO with an `orderId` (int), `item` (String), and `quantity` (int).
2. It MUST implement `Serializable` so JmsTemplate can send it via an `ObjectMessage`.
3. Add a standard `toString()` method for clean logging.

### Task 2: The Producer

1. Inside a Spring `@Service`, inject your `JmsTemplate`.
2. Write a method `sendOrder(Order order, String priorityFlag)` that does the following:
   - Uses `convertAndSend(destinationName, payload, messagePostProcessor)`
   - Inside the lambda/MessagePostProcessor, set a custom string property named `"Priority"` to the value passed in (e.g. `"HIGH"` or `"STANDARD"`).
   - *Hint:* `message.setStringProperty("Priority", priorityFlag); return message;`

### Task 3: The Priority Consumers

1. Create an `OrderListener` component.
2. We need TWO consumer methods listening to the SAME queue (`"order-processing-queue"`):
   - **Method A:** Annotate with `@JmsListener` and add `selector = "Priority = 'HIGH'"`. Have it log: `"URGENT: Processing High Priority Order: " + order`.
   - **Method B:** Annotate with `@JmsListener` and add `selector = "Priority = 'STANDARD'"`. Have it log: `"Standard: Processing Order: " + order`.

### Task 4: The Trigger Controller

1. Build a simple REST Controller with two endpoints:
   - `POST /api/orders/rush` (Creates an order and calls the producer with priority `"HIGH"`).
   - `POST /api/orders/standard` (Creates an order and calls the producer with priority `"STANDARD"`).

---

## Definition of Done

- When you use Postman to hit the `/rush` endpoint, ONLY Method A in your consumer component prints over the console.
- When you hit the `/standard` endpoint, ONLY Method B prints.
- We have verified that the ActiveMQ broker is successfully filtering messages based on custom JMS header properties before delivering them to listeners.
