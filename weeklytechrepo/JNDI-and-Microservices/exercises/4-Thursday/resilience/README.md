# Collaborative Lab: Architecting Resilience

## The Scenario

Your Pair Programming team has been assigned two microservices:

1. **The Order Service** (Client-facing)
2. **The Payment Service** (Internal Downstream)

The Payment Service is notoriously unstable. If it crashes, the Order Service must NOT crash. Customers must still be able to place an order, even if the payment takes 24 hours to process in the background via a batch job.

## Deliverables (Pair Programming)

1. **Developer 1 (Driver): Set up the Backend**
   - Create a Spring Boot application representing the `PaymentService`.
   - Expose a simple `GET /process` endpoint that sleeps for `5000` milliseconds (5 seconds) to simulate extreme lag, and then returns a 200 OK string.
2. **Developer 2 (Navigator): Set up the Frontend**
   - Create a Spring Boot application representing the `OrderService`.
   - Expose a `GET /checkout` endpoint.
   - Use `RestTemplate` to call `http://localhost:8081/process` (assuming Payment Service runs on `8081`).
3. **The Collaboration (Circuit Breaker):**
   - Developer 2 adds `resilience4j` dependencies to the Order Service.
   - Annotate the `/checkout` method with `@CircuitBreaker`.
   - Define a `fallbackMethod`. The fallback must print: *"Order saved to database. Payment will be processed asynchronously."*
4. **The Test:**
   - Both developers run their services. Hit the `/checkout` endpoint. It will take 5 seconds, but eventually succeed.
   - Developer 1 gracefully shuts down the `PaymentService` completely.
   - Developer 2 hits the `/checkout` endpoint again. It should *instantly* return the Fallback string without waiting 5 seconds.

## Definition of Done

- OrderService acts as a REST client calling the PaymentService.
- A functional Resilience4j Circuit Breaker intercepts the `ResourceAccessException` when the Payment Service is offline, returning the graceful fallback cleanly without throwing a 500 Server Error to the end user.
