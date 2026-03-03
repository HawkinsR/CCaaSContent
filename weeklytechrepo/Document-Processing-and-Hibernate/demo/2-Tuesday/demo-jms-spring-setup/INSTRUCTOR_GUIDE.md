# Demo: JMS with Spring Boot and ActiveMQ

## Overview

**Type:** Hybrid (Concept + Code)
**Time:** 45-60 minutes
**Goal:** Set up an embedded ActiveMQ broker in Spring Boot, send and receive messages using JmsTemplate and @JmsListener.

---

## Phase 1: The Concept (Whiteboard/Diagram)

**Time:** 10 minutes

1. Open `diagrams/jms-flow.mermaid`.
2. Walk through the flow:
   - The **Producer** sends a message to a **Queue** on the broker.
   - The **Broker** holds the message until a **Consumer** picks it up.
   - "This is asynchronous -- the producer does not wait for the consumer."
3. **Discussion:** "Why would we use a queue instead of calling the service directly? What happens if the consumer is down?"
4. Contrast with the pub/sub model: "A queue delivers to ONE consumer. A topic delivers to ALL subscribers."

---

## Phase 2: The Code (Live Implementation)

**Time:** 35-50 minutes

### Step 1: Add Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

### Step 2: Configure the Broker

Open `application.properties` and add:

```properties
spring.activemq.broker-url=vm://localhost?broker.persistent=false
spring.activemq.in-memory=true
spring.jms.pub-sub-domain=false
```

- "We are using an in-memory broker for this demo. In production, you would point to an external ActiveMQ instance."

### Step 3: Enable JMS

Open `code/JmsApp.java`:

- "`@EnableJms` tells Spring to start the JMS infrastructure and listen for `@JmsListener` methods."

### Step 4: Create the Producer

Open `code/OrderProducer.java`:

- "JmsTemplate is injected automatically -- Spring auto-configured it based on the ActiveMQ dependency."
- "`convertAndSend()` takes the queue name and the message payload."

### Step 5: Create the Consumer

Open `code/OrderConsumer.java`:

- "`@JmsListener(destination = \"order-queue\")` registers this method as a listener."
- "Spring handles polling, threading, and acknowledgment for us."

### Step 6: Create a Controller to Trigger Sending

Open `code/OrderController.java`:

- "This endpoint lets us trigger message sending from the browser or Postman."

### Step 7: Run and Test

1. Start the application.
2. `POST http://localhost:8080/api/orders` with body: `"Order 12345 - 3 Widgets"`
3. Watch the console output -- the consumer should log the received message.
4. Send several messages rapidly and show they arrive in order.

### Step 8: Demonstrate Decoupling

- Comment out the `OrderConsumer` class, restart, and send messages.
- "The messages are being queued but no one is consuming them. When we uncomment and restart, they will be there waiting."
- Uncomment, restart, and show the messages being consumed.

---

## Key Talking Points

- JMS decouples producers from consumers -- they do not need to be running simultaneously.
- `JmsTemplate` follows Spring's template pattern (like `JdbcTemplate`, `RestTemplate`).
- `@JmsListener` is declarative -- just annotate a method and Spring handles the rest.
- "Tomorrow we will explore JMS client patterns in more depth -- message types, selectors, and building a full client application."
