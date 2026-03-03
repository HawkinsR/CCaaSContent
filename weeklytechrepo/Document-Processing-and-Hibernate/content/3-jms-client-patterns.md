# JMS Client Patterns

## Learning Objectives

- Understand what a JMS client is and how it communicates with a message broker.
- Work with different JMS message types, including TextMessage and ObjectMessage.
- Build a complete JMS client application that sends and receives structured messages.
- Apply best practices for reliable message production and consumption.

## Why This Matters

On Tuesday, you learned the fundamentals of JMS and how Spring's `JmsTemplate` simplifies sending and receiving basic messages. Today we go deeper into the client side of messaging -- how to structure a production-quality JMS client that handles multiple message types, manages connections properly, and follows enterprise patterns. These patterns are directly applicable when building the asynchronous pipelines that connect document processing (XML/JSON from Monday and Tuesday) with the data persistence layer (Hibernate, starting later today).

## The Concept

### What Is a JMS Client?

A JMS client is any Java application that uses the JMS API to produce or consume messages through a message broker. A single application can act as both a producer and a consumer. The term "JMS client" refers to the application-side code, as opposed to the broker (server) infrastructure.

A JMS client typically performs the following lifecycle:

1. Obtain a `ConnectionFactory` from the broker (or Spring context).
2. Create a `Connection`.
3. Create a `Session` (with acknowledgment mode).
4. Create a `Destination` (queue or topic).
5. Create a `MessageProducer` or `MessageConsumer`.
6. Send or receive messages.
7. Close resources.

### Message Types in Detail

JMS defines several message types. Choosing the right type depends on the data being exchanged:

**TextMessage:**

The most common message type. Used when the payload is a plain string, often containing JSON or XML content.

```java
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
Queue queue = session.createQueue("document-queue");

MessageProducer producer = session.createProducer(queue);

TextMessage message = session.createTextMessage();
message.setText("{\"documentId\": 42, \"type\": \"invoice\"}");
producer.send(message);
```

**ObjectMessage:**

Used to send a serializable Java object directly. The consumer must have the same class on its classpath.

```java
Employee employee = new Employee(101, "Alice Johnson", "Engineering");

ObjectMessage message = session.createObjectMessage();
message.setObject(employee);
producer.send(message);
```

Note: `ObjectMessage` requires the payload class to implement `java.io.Serializable`. In modern applications, sending JSON via `TextMessage` is often preferred over `ObjectMessage` because it avoids class versioning issues.

**MapMessage:**

Sends a collection of key-value pairs, similar to a `Map<String, Object>`:

```java
MapMessage message = session.createMapMessage();
message.setString("name", "Alice Johnson");
message.setInt("id", 101);
message.setBoolean("active", true);
producer.send(message);
```

### Building a JMS Client with Spring

While the raw JMS API (shown above) works, Spring simplifies client development significantly. Here is a complete producer/consumer pair using Spring Boot.

**Producer Service:**

```java
@Service
public class DocumentProducer {

    private final JmsTemplate jmsTemplate;

    public DocumentProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendText(String destination, String content) {
        jmsTemplate.convertAndSend(destination, content);
    }

    public void sendObject(String destination, Serializable object) {
        jmsTemplate.convertAndSend(destination, object);
    }

    public void sendWithHeaders(String destination, String content, String correlationId) {
        jmsTemplate.convertAndSend(destination, content, message -> {
            message.setJMSCorrelationID(correlationId);
            message.setStringProperty("contentType", "application/json");
            return message;
        });
    }
}
```

The third method demonstrates a **MessagePostProcessor** -- a callback that lets you modify the JMS message headers before sending.

**Consumer Service:**

```java
@Component
public class DocumentConsumer {

    @JmsListener(destination = "document-queue")
    public void handleTextMessage(String content) {
        System.out.println("Received text: " + content);
    }

    @JmsListener(destination = "employee-queue")
    public void handleObjectMessage(Employee employee) {
        System.out.println("Received employee: " + employee.getName());
    }

    @JmsListener(destination = "document-queue", selector = "contentType = 'application/json'")
    public void handleJsonDocument(String json) {
        System.out.println("Received JSON document: " + json);
    }
}
```

Notice the `selector` attribute. **Message selectors** allow a consumer to filter messages based on header properties, so it only receives messages that match the criteria.

### Message Acknowledgment

JMS defines how consumers confirm receipt of messages:

| Mode                    | Behavior                                                          |
|-------------------------|-------------------------------------------------------------------|
| `AUTO_ACKNOWLEDGE`      | The session automatically acknowledges after the listener returns.|
| `CLIENT_ACKNOWLEDGE`    | The consumer must explicitly call `message.acknowledge()`.        |
| `DUPS_OK_ACKNOWLEDGE`   | Lazy acknowledgment; may result in duplicate delivery.            |
| `SESSION_TRANSACTED`    | Messages are acknowledged as part of a transaction commit.        |

Spring Boot defaults to `AUTO_ACKNOWLEDGE`, which is suitable for most use cases.

### Error Handling

When a `@JmsListener` method throws an exception, the message is typically redelivered by the broker. You can customize this behavior with an error handler:

```java
@Configuration
public class JmsErrorConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(t ->
            System.err.println("JMS error: " + t.getMessage())
        );
        return factory;
    }
}
```

### Best Practices

- **Prefer TextMessage with JSON** over ObjectMessage to avoid serialization and class-loading issues across services.
- **Use message selectors** to route messages to specialized consumers without creating separate queues.
- **Set correlation IDs** on request/reply patterns so the response can be matched to the original request.
- **Keep listener methods lightweight.** If processing is heavy, delegate to an async service to avoid blocking the listener thread pool.
- **Handle poison messages.** Configure a dead-letter queue (DLQ) on the broker to capture messages that fail repeatedly.

## Summary

- A JMS client is any application that produces or consumes messages via the JMS API.
- JMS supports multiple message types: TextMessage, ObjectMessage, MapMessage, BytesMessage, and StreamMessage.
- Spring's `JmsTemplate` and `@JmsListener` eliminate most boilerplate, providing clean producer and consumer abstractions.
- Message selectors, correlation IDs, and acknowledgment modes give you fine-grained control over message routing and reliability.
- These asynchronous patterns complement the document processing (XML/JSON) learned earlier this week and lay groundwork for the microservice communication patterns covered in Week 2.

## Additional Resources

- [Spring JMS Reference -- JmsTemplate](https://docs.spring.io/spring-framework/reference/integration/jms/sending.html)
- [Baeldung -- JMS with Spring](https://www.baeldung.com/spring-jms)
- [Apache ActiveMQ Documentation](https://activemq.apache.org/components/classic/documentation)
