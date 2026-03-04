package com.revature.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@SpringBootApplication
@RestController
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    // This method simulates calling a downstream Payment Service over REST.
    // The @CircuitBreaker intercepts exceptions or timeouts.
    @GetMapping("/order")
    @CircuitBreaker(name = "paymentCall", fallbackMethod = "paymentFallback")
    public String processOrder() {
        System.out.println("Attempting to reach Payment Service...");

        // Simulating the downstream Payment service crashing completely:
        throw new RuntimeException("Payment Service is unresponsive (503 Service Unavailable)");
    }

    // The Fallback method. This executes immediately when the Circuit is OPEN or
    // the call fails.
    // The parameter signature must exactly match the original method + the
    // Exception.
    public String paymentFallback(Throwable t) {
        System.out.println("Circuit Breaker triggered Fallback due to: " + t.getMessage());
        return "Graceful Fallback: Order received and placed in queue. Payment processing is currently offline. We will attempt payment later.";
    }
}
