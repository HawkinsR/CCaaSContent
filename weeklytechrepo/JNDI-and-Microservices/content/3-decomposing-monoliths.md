# Decomposing a Monolith

## Learning Objectives

- Identify strategies for breaking down a monolithic application.
- Understand how identifying business capabilities leads to microservice boundaries.
- Define the role of an API Gateway in a decomposed system.

## Why This Matters

Transitioning from a Monolith to a Microservices Architecture (MSA) is rarely done by building everything from scratch. Enterprise companies perform a gradual migration, often termed the "Strangler Fig Pattern." Understanding how to identify the boundaries (the "seams") within a large codebase to pull out independent services is the core job of a Solutions Architect. You must also understand how client applications negotiate this transition seamlessly using an API Gateway.

## The Concept

### Strategies for Decomposition

A monolith has all its code running in the same process, often accessing the same massive database schema. Decomposing it means splitting both the code and the data.

#### Decompose by Business Capability

This is the most common strategy. You define a microservice according to a specific business area (e.g., Shipping, Billing, Inventory, User Profile). The boundaries of these services correspond to business domains. For example, the Shipping team does not need to know the database structure of the User Profile team.

#### Decompose by Subdomain (Domain-Driven Design)

In an enterprise, a "Product" means something different to the Sales department (price, catalog image) than it does to the Inventory department (weight, shelf location). Domain-Driven Design (DDD) teaches us to create separate bounded contexts. Rather than one massive `Product` table, you might have a `CatalogService` and an `InventoryService`, each maintaining their own distinct view of a "Product."

### The Strangler Fig Pattern

Instead of rewriting the entire monolith at once (a high-risk strategy), organizations slowly replace specific functionalities with new microservices.

1. A new feature (or a heavily trafficked legacy feature) is built as a separate microservice.
2. The routing layer directs all traffic for that specific feature to the new microservice.
3. All other traffic continues to the old monolith.
4. Over time, as more services are extracted, the monolith shrinks until it is eventually retired (strangled).

### The API Gateway

When a monolith is split into 50 microservices, a frontend client (like a mobile app) faces a huge problem: Does it need to know the IP address of all 50 services? What if a client needs data from the User profile, the Order history, and the Product catalog just to render one screen?

An API Gateway solves this.

- **Single Point of Entry:** The API Gateway acts as the single entry point into the system for all clients.
- **Routing:** It routes requests, e.g., `/api/users` goes to the User Service, while `/api/orders` goes to the Order Service.
- **Aggregation:** It can gather data from multiple microservices and return a single cohesive response to the client.
- **Cross-Cutting Concerns:** Gateways often handle authentication, SSL termination, and rate-limiting, so the individual microservices don't have to.

## Summary

- Decomposing a monolith requires splitting applications by business capabilities or DDD subdomains.
- The Strangler Fig Pattern mitigates migration risk by replacing the monolith piece-by-piece over time.
- An API Gateway shields external clients from the internal complexity of dozens of different microservices, acting as a router and aggregator.

## Additional Resources

- [Martin Fowler: Strangler Fig Application](https://martinfowler.com/bliki/StranglerFigApplication.html)
- [Microservices.io: Decompose by Business Capability](https://microservices.io/patterns/decomposition/decompose-by-business-capability.html)
- [NGINX: API Gateway Pattern](https://www.nginx.com/learn/api-gateway/)
