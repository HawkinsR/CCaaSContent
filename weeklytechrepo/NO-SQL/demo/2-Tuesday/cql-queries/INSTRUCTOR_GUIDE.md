# Demo: Operations and the WHERE Clause Restrictions

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** We have a table. Now we need to put data into it and get data out.
2. **INSERTs:** In Cassandra, `INSERT` acts roughly like an "Upsert" in other databases. If the primary key already exists, it overwhelmingly just overwrites it. There are no cascading foreign key checks to slow down the write speed.
3. **SELECT Restrictions:** Because Cassandra is distributed across potentially hundreds of machines, we are mathematically forbidden from running a query that would force it to scan every machine (a full table scan). The `WHERE` clause MUST include the Partition Key.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/cql_queries.cql`.
2. **Walkthrough and Execute Iteratively:**
    * Run the `INSERT` statements. Note the `uuid()` function which generates a unique ID on the fly.
    * Run the valid `SELECT`. Show how passing `WHERE country_code = 'US'` instantly routes the query to the single server holding the US data partition.
    * **The Intentional Crash:** Run the invalid `SELECT` (`WHERE first_name = 'Alice'`).
    * Read the error aloud: `InvalidRequest: Cannot execute this query as it might involve data filtering and thus may have unpredictable performance.`
    * Explain *why*: Alice could be on Node 1, Node 50, or Node 500. Cassandra refuses to guess.
