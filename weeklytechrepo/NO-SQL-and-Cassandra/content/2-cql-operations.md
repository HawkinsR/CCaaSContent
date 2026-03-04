# CQL Operations (INSERT & SELECT)

## Learning Objectives

- Execute `INSERT` operations in Cassandra.
- Describe the "Upsert" nature of Cassandra mutations.
- Master the restrictions of the `SELECT` / `WHERE` clause in distributed querying.

## Why This Matters

Retrieving data from a relational database involves writing a SQL `SELECT` statement, where the query optimizer handles the hard work of searching the disk. In Cassandra, the engine prevents you from executing full table scans by default to protect the stability of the cluster. Understanding exactly *why* Cassandra restricts the `WHERE` clause—requiring you to query specifically by the Partition Key—is the defining hurdle of NoSQL data modeling.

## The Concept

### INSERT Data (Mutations)

Inserting a row in CQL looks identical to classical SQL. You specify the table name, the target columns, and the corresponding values.

#### The "Upsert" Philosophy

In relational databases, if you attempt to `INSERT` a row with a Primary Key that already exists, the database throws an error (Constraint Violation), protecting the existing data.
In Cassandra, an `INSERT` acts as an **Upsert** (Update + Insert).
If the Primary Key does not exist, a new row is created. If the Primary Key *already* exists, Cassandra quietly overwrites the old row with the new data. No error is thrown.

**(Note: This happens because checking if a row exists requires a Read across the distributed network *before* a Write, which destroys Cassandra's million-writes-per-second performance model.)**

### SELECT and The `WHERE` Clause Restrictions

To perform a highly optimized distributed Read, Cassandra must know exactly which node holds the data. It determines this by hashing the Partition Key.
Therefore, **you must query data by its Partition Key.**

If you issue the query: `SELECT * FROM users WHERE last_name = 'Smith';`

Cassandra will **reject** the query and throw a `InvalidRequestException`.
Why? Because the `last_name` column is not part of the Primary Key. To find all "Smiths," Cassandra would have to ask all 50 nodes in your cluster to scan every single row on their hard drives, tie up network traffic returning the results, and merge them on the Coordinator. This is called a **Full Cluster Scan** and is forbidden by default.

#### ALLOW FILTERING (The Anti-Pattern)

You *can* force Cassandra to perform a Full Cluster Scan by appending `ALLOW FILTERING` to the end of a forbidden query: `SELECT * FROM users WHERE last_name = 'Smith' ALLOW FILTERING;`

This must be strictly avoided in production. A query that takes 10 milliseconds when querying by a Partition Key will take 45 seconds using `ALLOW FILTERING` on a billion-row table, instantly slowing down the entire enterprise application.

### Understanding Clustering Columns

If your Primary Key consists of multiple columns: `PRIMARY KEY (user_id, created_at)`

- The first column (`user_id`) is the **Partition Key**. (Determines the Node).
- The second column (`created_at`) is the **Clustering Column**. (Determines how the data is sorted *on that specific Node*).

When writing `WHERE` clauses, you must provide the exact `user_id` first. You can then optionally provide `<, >, =, or BETWEEN` logical checks against the sorting/clustering column (`created_at`).

## Code Example

```sql
-- --- UPSERT OPERATIONS ---
-- An initial insert creates the row.
INSERT INTO users (user_id, first_name, last_name, age) 
VALUES (uuid(), 'Alice', 'Smith', 28);

-- The 'uuid()' function automatically generates a random UUID for the required partition key.

-- If a specific UUID is known, the second execute of this command will OVERWRITE 'Alice' with 'Alice Marie'.
INSERT INTO users (user_id, first_name) 
VALUES (123e4567-e89b-12d3-a456-426614174000, 'Alice Marie');


-- --- SELECT OPERATIONS ---

-- 1. Correct Query: Specifying the exact Partition Key
-- Cassandra hashes the UUID, goes straight to Node #4, and returns the row. (Millisecond execution).
SELECT * FROM users WHERE user_id = 123e4567-e89b-12d3-a456-426614174000;

-- 2. Forbidden Query: Specifying a non-key column
-- ERROR: InvalidRequestException (Cannot execute this query as it might involve data filtering).
-- SELECT * FROM users WHERE first_name = 'Alice';

-- 3. Forbidden Override: ALOW FILTERING (Anti-pattern!)
-- Forces all nodes to scan their entire disk.
SELECT * FROM users WHERE age > 25 ALLOW FILTERING;
```

## Summary

- `INSERT` operations behave as **Upserts**. They silently overwrite existing rows with the same Primary Key.
- High-performance READ operations dictate the rules: You **must** utilize the `WHERE` clause to specify the exact Partition Key to rapidly locate the node housing the data.
- Querying non-primary key columns forces expensive full table scans.
- Appending `ALLOW FILTERING` circumvents CQL safeguards and dramatically degrades cluster health; restrict its use to extremely small administrative tables.

## Additional Resources

- [DataStax: The Cassandra Query Language - SELECT](https://docs.datastax.com/en/cql-oss/3.3/cql/cql_reference/cqlSelect.html)
- [DataStax: The 'ALLOW FILTERING' Anti-pattern](https://www.datastax.com/blog/allow-filtering-explained)
