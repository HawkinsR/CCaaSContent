# Demo: Distributed Tracing with Micrometer

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** In a Monolith, if an error happens, you open the single `catalina.out` log file and look for the stack trace.
    In Microservices, a customer clicks "Checkout", which hits the API Gateway (Log File 1), which hits the Auth Service (Log File 2), which hits the Order Service (Log File 3), which hits the Payment Service (Log File 4). If the process crashes at the Payment Service, how does an engineer know which of the 5,000 requests in Log File 1 corresponds to the failure in Log File 4?
2. **The Trace ID Solution:** Distributed Tracing automatically assigns a unique UUID (a Trace ID) to the incoming HTTP request at the API edge. As the request leaps between microservices, that exact same Trace ID is forcefully injected into every single log entry on every single server.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/TracingController.java` (simulating Service A) and `code/application.yml`.
2. **Walkthrough the Code:**
    * Highlight the inclusion of the `micrometer-tracing-bridge-brave` dependency in the `pom.xml`. This automatically intercepts SLF4J logging and injects the Trace IDs.
    * Show the simple SLF4J `log.info()` statements. Notice nothing special is defined in the Java code itself.
3. **Run the App (Terminal):**
    * Start Service A.
    * Hit the `/start-trace` endpoint in Postman.
    * Observe the console logs. Notice the automatic prefix: `[service-a, 8de5c2... , 8de5c2...]`.
4. **Discussion:** Explain that `8de5c2...` is the Trace ID. If Service A used a `RestTemplate` to call Service B, Spring Boot automatically injects `X-B3-TraceId: 8de5c2...` into the HTTP Request Header, ensuring Service B seamlessly continues logging with the exact same identifier.
