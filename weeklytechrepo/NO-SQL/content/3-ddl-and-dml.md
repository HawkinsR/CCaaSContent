# Advanced Cassandra DDL, DML, and Security

## Learning Objectives

- Differentiate between Data Definition Language (DDL) and Data Manipulation Language (DML) in the context of wide-column stores.
- Master executing updates, batch statements, and deletions in Cassandra.
- Describe the mechanism of Tombstone deletion.
- Define Dynamic Data Masking (DDM) for regulatory security compliance.

## Why This Matters

As you transition away from basic local development and start designing schema configurations for highly sensitive corporate or user data (PII - Personally Identifiable Information), you must learn the advanced functionality of CQL. Furthermore, executing bulk database writes or masking sensitive columns from unauthorized end-users are mandatory requirements for enterprise Data Engineers.

## The Concept

### Data Definition Language (DDL)

DDL commands alter the fundamental structure of the database.

- **`CREATE` / `ALTER` / `DROP KEYSPACE`**: Managing the top-level container and its underlying replication strategy.
- **`CREATE` / `ALTER` / `DROP TABLE`**: Managing the column structures and primary keys.
  - *Note: While you can `ALTER` a table to add a new column in Cassandra, you absolutely **cannot** alter a table to modify the Primary Key (e.g., adding a clustering column). Modifying the Primary Key fundamentally alters the storage hashing algorithm; you must DROP the table and rebuild it from scratch.*

### Data Manipulation Language (DML)

DML commands mutate or read the actual data within the tables.

- **`INSERT`**: Upserts data.
- **`UPDATE`**: Also Upserts data. In Cassandra, there is fundamentally zero performance difference between an `INSERT` and an `UPDATE` under the hood. They are exactly identical operations to the storage engine.
- **`BATCH`**: Executes multiple `INSERT`, `UPDATE`, or `DELETE` statements at exactly the same time. BATCH guarantees that either all statements succeed, or none succeed (providing limited Atomicity).

#### Deletions and Tombstones

In a standard RDBMS, invoking `DELETE` instantly wipes the data off the hard drive.
In a distributed, eventually-consistent system, deleting data is extremely complicated. If Node #4 goes offline, and you explicitly DELETE 'Alice' from Node #5 and #6, what happens when Node #4 boots back up? Node #4 will tell the cluster, "I have Alice!", and the cluster will accidentally resurrect the deleted data.

To prevent this, Cassandra uses **Tombstones**. When you issue a `DELETE` statement, Cassandra writes a *brand new record* (a Tombstone) directly over the old data, marking it dead. When Node #4 reboots and presents 'Alice', the cluster compares timestamps, sees a newer Tombstone for 'Alice', and instructs Node #4 to accept the deletion. (Tombstones are permanently purged from the hard drive weeks later during a process called Compaction).

### Dynamic Data Masking (DDM)

Enterprise applications often store credit card numbers, Social Security Numbers, or internal salaries. Call center representatives need to view a user's profile to assist them, but they absolutely must not see the full 16-digit credit card number.

**Dynamic Data Masking (DDM)** obscures sensitive data dynamically query-by-query, directly inside the database engine, returning placeholder characters (like `XXXX-XXXX-XXXX-1234`) instead of the true underlying data.
Cassandra natively provides DDM masking functions in CQL. Using `mask_replace()` or `mask_inner()`, DBAs can define rules shielding unauthorized client integrations from viewing PII.

## Code Example

```sql
-- --- BATCH STATEMENTS (DML) ---
-- Ensures all listed mutations occur together in an atomic transaction across the distributed ring.
BEGIN BATCH
  INSERT INTO users (user_id, age) VALUES (uuid(), 25);
  UPDATE accounts SET balance = balance - 10 WHERE account_id = 'A-123';
APPLY BATCH;

-- --- DELETION AND TOMBSTONES ---
-- This generates a Tombstone marker; the underlying bytes are technically not erased instantly.
DELETE FROM users WHERE user_id = 123e4567-e89b-12d3-a456-426614174000;


-- --- DYNAMIC DATA MASKING (DDL & DML) ---
-- Creating a table with a natively masked column
CREATE TABLE employees (
    emp_id uuid PRIMARY KEY,
    name text,
    -- The actual salary is stored in the DB file, 
    -- but any standard SELECT will return the integer '0' instead of the true value.
    salary int MASKED WITH DEFAULT
);

INSERT INTO employees (emp_id, name, salary) VALUES (uuid(), 'Bob', 125000);

-- Querying the table outputs: Bob | 0
SELECT name, salary FROM employees;
```

## Summary

- **DDL** modifies Keyspaces and Tables. Modifying a Primary Key requires completely dropping and recreating the Table.
- **DML** modifies Row Data. `INSERT` and `UPDATE` execute the identical "Upsert" logic. `BATCH` groups mutations.
- **Deletions** create **Tombstones**, which are explicit localized markers indicating data is dead, ensuring the deletion correctly replicates to offline nodes when they reconnect.
- **Dynamic Data Masking (DDM)** is a native security feature that dynamically obscures sensitive data returned by `SELECT` queries to limit PII exposure to client applications.

## Additional Resources

- [Apache Cassandra Documentation: Tombstones](https://cassandra.apache.org/doc/latest/cassandra/architecture/storage_engine.html#deletion)
- [DataStax: Dynamic Data Masking](https://docs.datastax.com/en/astra-serverless/docs/security/dynamic-data-masking.html)
