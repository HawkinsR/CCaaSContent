# Demo: Implementing a Circuit Breaker with Resilience4j

## Phase 1: The Concept

**Time:** 10 mins

1. **Context:** In a Microservices Architecture, Service A (OrderService) calls Service B (PaymentService). What happens if Service B crashes or experiences incredible network latency?
2. **The Cascading Failure:** Explain that if Service A waits indefinitely for Service B, Service A's thread pool fills up, and then Service A crashes. Then Service C (which calls A) also crashes. This is a cascading failure.
3. **The Circuit Breaker Pattern:** Explain the three states: Closed (flowing normally), Open (Service B is failing, so don't even try to call it; instantly return an error or fallback), and Half-Open (let one request through to see if Service B recovered).

## Phase 2: The Code (Live Implementation)

**Time:** 20 mins

1. Open `code/OrderServiceApplication.java`.
2. **Walkthrough the Code:**
    * Highlight the `@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")` annotation on the `processOrder()` method.
    * Show the mock implementation of `processOrder()` throwing a `RuntimeException` to simulate the internal PaymentService crashing.
    * Show the `paymentFallback()` method. Emphasize that its signature (parameters and return type) must perfectly match the original method, plus a `Throwable` parameter.
3. **Run the App:**
    * Hit the endpoint. Instead of receiving a massive Stack Trace 500 Error, the user seamlessly receives the Fallback message ("Payment Gateway offline, order saved for later").
    * *Discussion:* Explain how this provides a graceful degradation of service rather than a total system collapse.
