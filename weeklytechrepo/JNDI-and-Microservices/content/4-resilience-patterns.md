# Resilience Patterns

## Learning Objectives

- Define resilience in distributed systems.
- Explain Load Balancing strategies.
- Understand the Circuit Breaker design pattern and how it prevents cascading failures.

## Why This Matters

In a monolithic application, if a method call fails, it's usually due to a clear bug (like a NullPointerException) and the stack trace is easy to follow. In a Microservice Architecture, services communicate over a network, and networks are inherently unreliable. A downstream service might be slow, unreachable, or crashing. If an application makes a synchronous REST call to a dead service, the application will hang, eating up precious threads until it, too, crashes. Resilience patterns are essential to make your distributed system robust enough to survive partial failures without taking down the entire enterprise.

## The Concept

### The Reality of Distributed Systems

The "Fallacies of Distributed Computing" remind us that networks are not reliable, latency is not zero, and bandwidth is not infinite. Whenever Service A calls Service B over HTTP, you must assume the call might fail or take 30 seconds to return.

### Load Balancing

Before we can implement advanced resilience, we need a way to distribute traffic evenly across multiple healthy instances of a service. This prevents a single instance from becoming a bottleneck (and eventually crashing under load).

#### Strategies

1. **Round Robin:** The simplest algorithm. Requests are distributed sequentially (Instance 1, Instance 2, Instance 3, then back to 1). Best when all instances have identical computing power.
2. **Weighted Round Robin:** Some instances are assigned a higher "weight" (perhaps they run on more powerful servers) and receive a proportionally larger share of traffic.
3. **Least Connections:** The Load Balancer monitors the active connections to each instance and routes new requests to the instance with the fewest current connections. Excellent for handling requests that vary wildly in execution time.
4. **IP Hash:** A hash is calculated based on the client's IP address. This ensures a specific client is always routed to the same instance (useful for "sticky sessions" or localized caching, though less common in stateless microservices).

*Note: In Spring Cloud environments using Eureka, load balancing is often handled client-side by Spring Cloud LoadBalancer, abstracting these algorithms from the developer.*

### The Circuit Breaker Design Pattern

Imagine a scenario: The `OrderService` needs to call the `InventoryService`. The `InventoryService` database is currently experiencing an outage, making it extremely slow.

Without a Circuit Breaker:

1. The `OrderService` sends a request.
2. It waits 10 seconds for a timeout.
3. Every new customer triggers another waiting thread in the `OrderService`.
4. Eventually, the `OrderService` runs out of threads and crashes too. This is a **cascading failure.**

The Circuit Breaker pattern acts exactly like the electrical breakers in your home, protecting the system from power surges. It "wraps" calls to external services.

#### The Three States of a Circuit Breaker

1. **CLOSED:** Everything is operating normally. Requests pass straight through to the downstream `InventoryService`. The circuit breaker monitors success and failure rates.
2. **OPEN:** The failure rate (e.g., timeouts, 500 errors) has exceeded a predefined threshold (e.g., 50% failures in a 10-second rolling window). The circuit is explicitly "broken." **All subsequent requests immediately fail** without even attempting to call the `InventoryService`.
    - *Crucially: During the OPEN state, the Circuit Breaker executes a "Fallback Method." This might return a cached response, a default value, or a polite error message to the user, ensuring the `OrderService` never hangs.*
3. **HALF-OPEN:** After a configured cooldown period, the Circuit Breaker allows a *single* test request to pass through to the `InventoryService`.
    - If the test request succeeds, the downstream service has recovered. The circuit transitions back to **CLOSED**.
    - If the test request fails, the service is still down. The circuit transitions back to **OPEN** and resets the cooldown timer.

## Summary

- In an MSA, you must design for failure. Downstream services will eventually become slow or unreachable.
- Load Balancing algorithms distribute incoming traffic across the server fleet to maximize throughput and minimize response times.
- The **Circuit Breaker Pattern** wraps external calls. It monitors failures and "opens" (trips) when error thresholds are crossed, preventing resource exhaustion and cascading failures.
- A well-designed resilient system always provides a "Fallback" when a circuit is open, ensuring a graceful degradation of the user experience.

## Additional Resources

- [Martin Fowler: CircuitBreaker Pattern](https://martinfowler.com/bliki/CircuitBreaker.html)
- [Resilience4j Official Documentation](https://resilience4j.readme.io/)
