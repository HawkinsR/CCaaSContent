# Demo: Monolithic Architecture Bottlenecks

## Phase 1: The Concept (Visual Diagram)

**Time:** 15 mins

1. Open `diagrams/tangled-monolith.mermaid` in a Markdown viewer.
2. **Walkthrough the Architecture:**
    * Point out that the User Interface, Authentication logic, Billing logic, and Inventory logic all live inside the exact same `.war` or `.jar` file.
    * Trace a single user request flowing through multiple deeply coupled packages before hitting the single, unified database.
3. **Highlight the Pain Points:**
    * **Blast Radius:** What happens if there is a memory leak in the `BillingPackage`? *Answer: The entire server crashes, taking down the UI and Authentication with it.*
    * **Scaling:** What if Black Friday hits and the `InventoryPackage` is receiving 10,000 requests a second, but `Billing` only receives 10? *Answer: You cannot scale just Inventory. You must clone the entire massive monolith across 10 servers, wasting massive amounts of RAM.*
    * **Deployment Friction:** To fix a typo in the UI, the entire massive `application.war` must be recompiled and deployed, risking breaking backend logic.
4. **Discussion:** Transition the class to discussing how drawing boundaries around these colored packages and turning them into standalone network applications forms the basis of Microservices.
