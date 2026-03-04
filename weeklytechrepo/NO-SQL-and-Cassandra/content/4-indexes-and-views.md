# Indexes and Materialized Views

## Learning Objectives

- Compare Secondary Indexes and Materialized Views.
- Understand why Secondary Indexes cause massive performance degredation in distributed systems.
- Create a Materialized View to support querying by a non-primary key column.

## Why This Matters

As discussed previously, Cassandra explicitly rejects any `SELECT` query that does not filter by the Partition Key. However, business requirements often demand finding a user by their email address instead of their internal UUID. Data modeling in Cassandra is driven entirely by the queries you need to run. If you need to query the same data in two different ways, you must write that data twice. Understanding the correct architectural pattern (Materialized Views) versus the anti-pattern (Secondary Indexes) is critical for system performance.

## The Concept

### The Query-Driven Architecture

In SQL, you build a normalized schema (Users, Addresses, Orders) and then write complex `JOIN` queries to extract the data you need.
In Cassandra, you perform the opposite: You identify every single query the business needs *first* (e.g., "Find Orders by User ID," "Find Orders by Date"), and you create a separate denormalized table for *each* specific query. **Data duplication is expected and encouraged.** Storage is cheap; CPU calculation time across 50 nodes is extremely expensive.

### Secondary Indexes

If you have a `users` table where `user_id` is the Primary Key, but you need to query by `email_address`, you *can* create a Secondary Index on the `email_address` column.

```sql
CREATE INDEX user_email_idx ON users(email_address);
SELECT * FROM users WHERE email_address = 'alice@example.com'; 
-- This will now succeed without ALLOW FILTERING.
```

#### The Performance Death Trap

Secondary Indexes in Cassandra are profoundly dangerous.
When the Coordinator node receives the query `SELECT * FROM users WHERE email_address = 'alice@example.com'`, it hashes `alice@example.com`. But the data was originally distributed by hashing the `user_id`! The Coordinator does not know which node holds the data.

Therefore, the Coordinator is forced to broadcast the `SELECT` query to **every single node in the cluster**. Each node checks its local secondary index and returns the result. This causes immense network traffic and latency spikes. Secondary Indexes should generally be avoided unless you are entirely certain the queried value is stored locally on the specific node you are connecting to.

### Materialized Views

If Secondary Indexes are dangerous, you must duplicate the data into a brand new table tailored for the exact query you need. A **Materialized View** automates this duplication.

A Materialized View creates a new table, built entirely from an existing "Base Table." The View automatically replicates data from the Base Table, but it has a *completely completely different Primary Key*.

#### How it works

1. You have a base `users` table (Partition Key: `user_id`).
2. You create a Materialized View called `users_by_email` (Partition Key: `email_address`).
3. When your application executes an `INSERT INTO users`, Cassandra automatically and asynchronously duplicates that exact same data and executes an `INSERT INTO users_by_email` in the background.
4. Your application can now execute extremely fast, 1ms queries: `SELECT * FROM users_by_email WHERE email_address = 'alice@example.com'`.

Because `email_address` is the Primary Key of the View, the Coordinator hashes it, knows exactly which node owns the data, and skips broadcasting the query to the entire cluster.

## Code Example

```sql
-- --- 1. THE BASE TABLE ---
CREATE TABLE users (
    user_id uuid PRIMARY KEY,
    first_name text,
    last_name text,
    email_address text
);

-- --- 2. THE MATERIALIZED VIEW ---
-- The 'AS SELECT' clause defines what columns to copy over.
CREATE MATERIALIZED VIEW users_by_email AS
    SELECT * 
    FROM users
    -- We MUST ensure the new primary key column is never null 
    WHERE email_address IS NOT NULL AND user_id IS NOT NULL
    -- We flip the clustering: Email is now the Partition Key!
    -- user_id is the Clustering Column guaranteeing row uniqueness.
    PRIMARY KEY (email_address, user_id);

-- --- 3. EXECUTING QUERIES ---

-- This INSERT writes to 'users' and cascades automatically to 'users_by_email'
INSERT INTO users (user_id, first_name, email_address) VALUES (uuid(), 'Bob', 'bob@test.com');

-- This SELECT uses the new highly-optimized View.
-- It executes instantly in O(1) time because the Coordinator knows exactly where 'bob@test.com' lives.
SELECT * FROM users_by_email WHERE email_address = 'bob@test.com';
```

## Summary

- Cassandra requires a **query-driven data model**: You must duplicate data into new tables tailored for specific `SELECT` requirements.
- **Secondary Indexes** force devastating full-cluster broadcasts and are an anti-pattern for large topologies.
- **Materialized Views** automate data duplication. They create a new table from a Base Table with a redesigned Primary Key optimized for a specific query structure.

## Additional Resources

- [DataStax: Secondary Indexes in Cassandra](https://docs.datastax.com/en/cql-oss/3.3/cql/cql_using/useWhenIndex.html)
- [DataStax: Materialized Views](https://docs.datastax.com/en/cql-oss/3.3/cql/cql_using/useCreateMV.html)
