package com.example.demoapp.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer that listens for messages on the "order-queue".
 * Spring automatically polls the queue and invokes this method.
 */
@Component
public class OrderConsumer {

    @JmsListener(destination = "order-queue")
    public void receiveOrder(String orderDetails) {
        System.out.println("[CONSUMER] Received from order-queue: " + orderDetails);
    }
}
