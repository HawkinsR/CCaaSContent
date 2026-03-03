package com.example.demoapp.service;

import com.example.demoapp.model.Document;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Producer demonstrating multiple message types and message post-processors.
 */
@Service
public class DocumentProducer {

    private final JmsTemplate jmsTemplate;

    public DocumentProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /** Send a plain text message. */
    public void sendText(String destination, String content) {
        jmsTemplate.convertAndSend(destination, content);
        System.out.println("[PRODUCER] Sent text to " + destination);
    }

    /** Send a serializable object (Document). */
    public void sendObject(String destination, Document document) {
        jmsTemplate.convertAndSend(destination, (Serializable) document);
        System.out.println("[PRODUCER] Sent object to " + destination + ": " + document);
    }

    /** Send a message with custom headers using a MessagePostProcessor. */
    public void sendWithPriority(String destination, String content, String priority) {
        jmsTemplate.convertAndSend(destination, content, message -> {
            message.setStringProperty("priority", priority);
            message.setJMSCorrelationID("corr-" + System.currentTimeMillis());
            return message;
        });
        System.out.println("[PRODUCER] Sent priority=" + priority + " to " + destination);
    }
}
