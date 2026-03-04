# Challenge: E-Commerce Microservice Decomposition

## The Scenario

You are the new Lead Architect for `ShopMart.com`, an aging E-Commerce site. Currently, `ShopMart.com` is a massive Java Monolith running on a single 128GB RAM server.
It contains 5 main packages representing the entire business logic:

1. `UserAuthentication`
2. `ProductCatalog`
3. `ShoppingCart`
4. `CreditCardProcessing`
5. `EmailNotifications`

The CEO complains that every time the developers update the `EmailNotifications` package, they accidentally break the `CreditCardProcessing` package and nobody can buy anything.

Your job is to design a target state Microservices Architecture (MSA) using Mermaid.

## Deliverables

1. Navigate to `templates/msa-architecture.mermaid`.
2. Convert the 5 packages into standalone Microservices.
3. Add a foundational **API Gateway** component at the edge. The Client Web Browser must *only* talk to the API Gateway. The API Gateway then forwards traffic to the internal microservices.
4. Define the communication arrows. (e.g., Does the API Gateway talk to the Database directly? No. It talks to the microservice that owns the database).

## Definition of Done

- A valid `.mermaid` diagram exists mapping the flow from Client -> API Gateway -> 5 distinct Microservices.
- No two microservices share the same physical database (e.g., The `ProductCatalog` Database is completely isolated from the `UserAuthentication` Database).
