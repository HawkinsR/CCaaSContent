package com.example.demoapp.listener;

import com.example.demoapp.model.Document;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer demonstrating basic listening and filtered listening with selectors.
 */
@Component
public class DocumentConsumer {

    /** Receives all text messages from the document-queue. */
    @JmsListener(destination = "document-queue")
    public void handleTextMessage(String content) {
        System.out.println("[CONSUMER] Text message: " + content);
    }

    /** Receives serialized Document objects from the object-queue. */
    @JmsListener(destination = "object-queue")
    public void handleObjectMessage(Document document) {
        System.out.println("[CONSUMER] Object message: " + document);
    }

    /**
     * Receives ONLY messages where the "priority" header equals "HIGH".
     * The selector is evaluated at the broker level -- non-matching messages
     * are never delivered to this listener.
     */
    @JmsListener(destination = "priority-queue", selector = "priority = 'HIGH'")
    public void handleHighPriority(String content) {
        System.out.println("[CONSUMER] HIGH PRIORITY: " + content);
    }
}
