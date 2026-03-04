# CQL Basics

## Learning Objectives

- Differentiate Cassandra Query Language (CQL) from standard SQL.
- Create Keyspaces and Tables in Cassandra.
- Identify core Cassandra data types.

## Why This Matters

While Cassandra handles vast amounts of distributed data across dozens of nodes, developers need a human-readable interface to interact with that data. Cassandra Query Language (CQL) was designed specifically to look and perform almost identically to SQL to lower the barrier to entry for developers migrating from relational databases. However, underneath the hood, the two languages execute violently different operations. Understanding how to create Keyspaces (databases) and Tables correctly in CQL is the foundation of Cassandra data modeling.

## The Concept

### CQL vs SQL

Cassandra Query Language looks exactly like standard SQL: `SELECT name, age FROM users WHERE id = 123`.
However, Cassandra is a Wide-Column NoSQL store, not a relational database. It lacks standard SQL features:

1. **NO JOINS:** You cannot `JOIN` two tables together in Cassandra.
2. **No Subqueries:** You cannot execute embedded `SELECT` statements (e.g., `WHERE id IN (SELECT id from others)`).
3. **No Foreign Keys:** Because there are no relationships between tables, there is no concept of referential integrity (Foreign Keys).

These limitations exist intentionally to ensure queries execute in milliseconds across distributed hardware.

### Keyspaces (The Database Equivalent)

In a relational database, you organize tables inside a "Database." In Cassandra, the top-level container that holds your tables is called a **Keyspace**.
Crucially, the Keyspace is where you define your **Replication Strategy**: How many total copies of this data should exist (Replication Factor), and onto which physical Data Centers should those copies be placed?

#### Creating a Keyspace

```sql
-- SimpleStrategy is for single data-center clusters.
-- replication_factor = 3 means 3 total copies of the data will be stored across the ring.
CREATE KEYSPACE IF NOT EXISTS e_commerce
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 3};

-- Switch the current context to use this specific keyspace
USE e_commerce;
```

### Tables and Primary Keys

Creating a table in CQL mirrors SQL. However, the Primary Key structure is infinitely more important in a distributed system because the Primary Key determines *exactly which server* stores the data.

#### The Partition Key

Every table must have a Primary Key. The first part of that key is the **Partition Key**.
Cassandra hashes the Partition Key to determine which node in the ring stores the row.

#### CQL Data Types

CQL supports standard types that map cleanly to Java or Python:

- **Text/Strings:** `text` or `varchar` (UTF-8 encoded strings)
- **Numbers:** `int` (32-bit), `bigint` (64-bit), `float`, `double`
- **Unique Identifiers:** `uuid` or `timeuuid` (Version 1 UUIDs sortable by time, crucial for distributed databases)
- **Collections:** `set`, `list`, `map` (Allows storing an array of emails directly in a single column without needing a secondary joined table).

## Code Example

```sql
-- Switch to the appropriate Keyspace
USE e_commerce;

-- Create a table combining fundamental Data Types
CREATE TABLE IF NOT EXISTS users (
    -- The PRIMARY KEY is the user_id.
    -- This means user_id is the Partition Key.
    user_id uuid PRIMARY KEY,
    
    first_name text,
    last_name text,
    age int,
    
    -- A Cassandra 'List' collection type.
    -- No JOIN table is required to store multiple emails for one user!
    emails list<text>
);

-- Note: Because 'user_id' is the unique identifier, appending a new row
-- creates a unique partition. If a million users exist, there are a million partitions.
```

## Summary

- **CQL** fundamentally mimics SQL syntax but lacks distributed "crutches" like `JOIN`s and Foreign Keys.
- A **Keyspace** replaces the concept of a Database and dictates the Replication strategy (e.g., Replication Factor: 3).
- Tables require careful Primary Key definition because the **Partition Key** dictates physical node placement on the hardware ring.
- CQL supports robust **Collection Types** (`list`, `set`, `map`) to enable data denormalization.

## Additional Resources

- [DataStax: CQL Reference - Keyspaces](https://docs.datastax.com/en/cql-oss/3.3/cql/cql_reference/cqlCreateKeyspace.html)
- [Apache Cassandra: CQL Data Types](https://cassandra.apache.org/doc/latest/cassandra/cql/types.html)
