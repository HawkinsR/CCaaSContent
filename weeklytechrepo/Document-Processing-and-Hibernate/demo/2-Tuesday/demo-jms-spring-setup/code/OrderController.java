package com.example.demoapp.controller;

import com.example.demoapp.service.OrderProducer;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that triggers JMS message sending for demo purposes.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    /**
     * POST /api/orders
     * Accepts an order string and sends it to the JMS queue.
     */
    @PostMapping
    public String placeOrder(@RequestBody String order) {
        orderProducer.sendOrder(order);
        return "Order placed successfully";
    }
}
