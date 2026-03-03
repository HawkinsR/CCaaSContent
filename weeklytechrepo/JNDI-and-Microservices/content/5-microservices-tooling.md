# Microservices Tooling

## Learning Objectives

- Explain the role of Containerization (Docker) in a microservices ecosystem.
- Understand the need for Orchestration (Kubernetes).
- Describe how Distributed Tracing (Zipkin/Sleuth/Micrometer) and Monitoring (Prometheus/Grafana) solve operational observability challenges.

## Why This Matters

If you decompose a monolith into 50 microservices but manually start each one by running `java -jar` on a virtual machine, you have not succeeded; you have simply created an unmanageable deployment nightmare. Microservices require a massive shift in Operational (Ops) tooling. To gain the agility benefits of an MSA, developers must understand how their applications are packaged, run, and monitored by the infrastructure team.

## The Concept

### Containerization (Docker)

In the past, developers wrote code that ran perfectly on their laptops but failed in production due to environmental differences (wrong Java version, missing environment variables).

**Docker** solves this by packaging the application code, its dependencies, the runtime engine (the JRE), and even the OS configuration into a single, standardized, executable unit called a **Container**.

If a Docker container runs on your laptop, it is guaranteed to run identically on the QA server and the Production cloud cluster. This solves the "It works on my machine!" problem permanently.

### Orchestration

If you have 50 services, and each service needs 3 instances running for high availability, you are managing 150 Docker containers.

Who ensures that if a container crashes, a new one is instantly started? Who handles rolling updates so that customers don't experience downtime when you deploy v2.0 of the `OrderService`? Who provisions the virtual network so the containers can talk to one another securely?

This is **Container Orchestration**. While Docker Swarm exists, **Kubernetes (K8s)** has become the absolute industry standard for this task. Kubernetes acts like the conductor of an orchestra, ensuring all the containers play together correctly.

### Observability: The Big Challenge

#### Centralized Monitoring (Prometheus & Grafana)

When an API call to a monolith fails, you check one log file. When a user clicks "Checkout" in an MSA, that request might travel through the Gateway -> Cart Service -> Shipping Service -> Payment Service -> Email Service.

If the request takes 10 seconds and times out, which of those 5 services is the bottleneck?

You need **Monitoring**. Tools like *Prometheus* scrape metrics (CPU usage, memory consumption, HTTP 200 vs 500 error rates) from every microservice every few seconds. *Grafana* provides the visual dashboards that allow engineers to see these metrics in real-time, instantly highlighting anomalous spikes in latency or error rates.

#### Distributed Tracing (Micrometer Tracing / Zipkin)

Even with metrics, finding a specific failed request in the logs of 5 different services is impossible if you don't know they are related.

**Distributed Tracing** solves this.

1. When a request hits the API Gateway, the gateway generates a unique ID (a `Trace ID`).
2. The gateway injects this `Trace ID` into the HTTP Headers of the request it sends downstream.
3. Every microservice that receives the request logs that exact same `Trace ID` alongside its own internal messages. It also generates a `Span ID` to measure the exact time it spent processing its portion of the work.
4. It passes the `Trace ID` along in the headers to the next service in the chain.

Tools like *Zipkin* or *Jaeger* aggregate these logs. By searching for a single `Trace ID`, an engineer can see a beautiful waterfall diagram showing exactly how the request hopped from service to service, and exactly where it failed or stalled.

## Summary

- **Docker** standardizes deployment by packaging the application and its environment into a reproducible Container.
- **Kubernetes** orchestrates thousands of containers, handling scaling, networking, and self-healing.
- **Monitoring (Prometheus/Grafana)** provides numerical visibility into the health of the entire cluster.
- **Distributed Tracing (Zipkin/Micrometer)** injects a `Trace ID` to track a single user's request journey linearly across dozens of independent microservice logs.

## Additional Resources

- [Docker Curriculum](https://docker-curriculum.com/)
- [Microservices.io: Microservice Observability Patterns](https://microservices.io/patterns/observability/distributed-tracing.html)
- [Spring Cloud Sleuth / Micrometer Tracing](https://micrometer.io/docs/tracing)
