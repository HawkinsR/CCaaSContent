package com.example.demoapp.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer service that sends messages to a JMS queue.
 * JmsTemplate is auto-configured by Spring Boot when ActiveMQ is on the
 * classpath.
 */
@Service
public class OrderProducer {

    private final JmsTemplate jmsTemplate;

    public OrderProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sends a text message to the "order-queue".
     */
    public void sendOrder(String orderDetails) {
        jmsTemplate.convertAndSend("order-queue", orderDetails);
        System.out.println("[PRODUCER] Sent to order-queue: " + orderDetails);
    }
}
