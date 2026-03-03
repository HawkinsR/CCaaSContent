# Discovery Services

## Learning Objectives

- Explain the necessity of Service Discovery in a microservice architecture.
- Differentiate between Client-Side and Server-Side discovery.
- Understand the role of tools like Netflix Eureka Server and HashiCorp Consul.

## Why This Matters

In a monolith, components invoke one another using simple language-level method calls because they share the same memory layout. In a Microservice Architecture (MSA), services run on different machines across a network. Furthermore, because microservices dynamically scale up and down in cloud environments, their IP addresses and ports change constantly. Hardcoding a URL like `http://10.0.0.4:8080/api/users` into your Order Service will break as soon as that specific User Service instance restarts or scales. Service Discovery solves this fundamental networking problem allowing services to find each other dynamically.

## The Concept

### The Problem: Ephemeral Infrastructure

In the cloud, instances of your microservices are ephemeral (short-lived). They might scale up to 10 instances during Black Friday and scale down to 2 instances at midnight. They might crash and be restarted on a completely different virtual machine with a new IP address. A mechanism is required for services to locate each other dynamically without human intervention.

### Service Registries

A Service Registry is the "phone book" of your microservices ecosystem. It is a highly available database containing the network locations (IP address and port) of all running service instances.

When a microservice boots up (e.g., the `InventoryService`), it registers itself with the Service Registry, providing its IP and port. It also sends periodic "heartbeats" to prove it is still alive. If the registry stops receiving heartbeats, it removes that instance from its database.

### Client-Side vs Server-Side Discovery

1. **Client-Side Discovery (e.g., Netflix Eureka + Ribbon/Spring Cloud LoadBalancer):**
    The client service (e.g., `OrderService`) asks the Service Registry for all available locactions of the `InventoryService`. The Registry returns a list of [IP:Port] pairs. The client then uses a load-balancing algorithm locally to choose one instance and makes the HTTP request directly to it.

2. **Server-Side Discovery (e.g., AWS ALB, Kubernetes):**
    The client service sends its request to a Router (or Load Balancer) at a known, static address. The Router queries the Service Registry and forwards the request to an available instance. The client is completely unaware of the Service Registry.

### Common Tools

#### Netflix Eureka Server

Pioneered by Netflix, Eureka is a client-side service discovery pattern framework. It provides a REST-based server (the Registry) and a Java-based client component that you embed in your Spring Boot microservices. Spring Cloud provides excellent integration, allowing you to turn a standard Spring Boot app into a Eureka Server with a single `@EnableEurekaServer` annotation.

#### HashiCorp Consul

Consul is a more comprehensive tool often favored in enterprise Kubernetes environments or polyglot architectures (environments with many languages like Go, Python, and Java). Aside from service discovery, it offers a highly advanced distributed Key-Value store, health checking at the OS network level, and service-mesh capabilities securely encrypting traffic between services.

## Summary

- Because microservice IP addresses constantly change, hardcoding URLs is an anti-pattern.
- A **Service Registry** keeps a dynamic, real-time list of all healthy service instances.
- Services register on startup and send heartbeats to maintain their registration.
- **Client-Side Discovery** (like Eureka) involves the client querying the registry and load balancing locally.
- **Server-Side Discovery** places a router/load-balancer between the client and the registry.

## Additional Resources

- [Spring Cloud Netflix Documentation](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html)
- [Microservices.io: Service Discovery Pattern](https://microservices.io/patterns/client-side-discovery.html)
