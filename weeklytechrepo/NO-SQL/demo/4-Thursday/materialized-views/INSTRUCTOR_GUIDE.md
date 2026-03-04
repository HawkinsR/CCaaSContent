# Demo: Materialized Views

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Remember on Tuesday when Cassandra crashed because we tried to search `users_by_country` by a user's `first_name`? It forced a full-table scan because `first_name` wasn't the Partition Key.
2. **The Secondary Index Problem:** We could add a Secondary Index to `first_name` (`CREATE INDEX ON users_by_country(first_name);`). However, in a 500-node cluster, a Secondary Index query still has to ask EVERY SINGLE NODE "Do you have anyone named Alice?". This is terribly slow at scale.
3. **The Materialized View Solution:** A Materialized View solves this by *physically duplicating the data* under the hood, but organizing it by a newly defined Partition Key (e.g., `first_name`). When we insert into the base table, Cassandra automatically handles duplicating that insert into the View.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/materialized_view.cql`.
2. **Walkthrough the Code:**
    * Show `CREATE MATERIALIZED VIEW`.
    * Walk through the `AS SELECT` clause. We define exactly which columns from the base table we want duplicated into the view.
    * Show the `WHERE` clauses. Materialized Views require that all components of the *new* Primary Key are explicitly declared `IS NOT NULL`.
    * Show the new `PRIMARY KEY ((first_name), country_code, user_id)`. The data is now partitioned by `first_name`!
3. **Execute the Script:**
    * Paste the `CREATE` statement into `cqlsh`.
    * Run the `SELECT` query against the View. It now works perfectly without a full table scan because `first_name` is the new Partition Key.
