# Collaborative Lab: Query Optimization

## The Scenario

Your Pair Programming team has been tasked with fixing a critical bug. The E-Commerce application is timing out when users try to view their "Order History".

The Junior Developer who left the company modeled the `orders` table to partition by the `order_id` because it felt like a Primary Key. However, the Frontend application actually loads the page by querying all orders for a specific `customer_id`.

Because `customer_id` is not the Partition Key, Cassandra is refusing to execute the query without a dreaded `ALLOW FILTERING` clause, which invokes a cluster-wide table scan and times out.

## Deliverables (Pair Programming)

1. Navigate to the `starter_code` directory and open `optimization_lab.cql`.
2. Review the Junior Developer's flawed table definition and the two mock data insertions. You may run these in `cqlsh` to create the table.
3. **The Challenge:** Discuss with your pair how to solve this. Because this table is live in production containing millions of records, you **cannot dropping or altering the base `orders` table**.
4. **The Fix:** Write a `CREATE MATERIALIZED VIEW` statement named `orders_by_customer`.
5. You must promote `customer_id` to be the new Partition Key.
6. Make `order_date` the first Clustering Column so the orders are naturally sorted by date.
7. Make `order_id` the second Clustering Column to ensure absolute uniqueness.
8. Write a mathematically safe, highly-performant `SELECT` statement against your new view to prove you can fetch Customer #101's order history.

## Definition of Done

- A Materialized View is successfully created based on the legacy `orders` table.
- The `customer_id` acts as the Partition Key.
- A `SELECT` statement successfully outputs the two orders belonging to Customer 101 without requiring the `ALLOW FILTERING` performance-killer.
