# Demo: Data Modeling and Table Creation

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** In traditional SQL, we model our data around the *Entities* (Users, Posts, Comments) to achieve Normalization. In Cassandra, we model our data around the *Queries* we intend to run.
2. **The Primary Key:** This is the most crucial difference from SQL.
    * **Partition Key:** The first part of the Primary Key. It determines exactly which physical server (node) in the cluster will store the row.
    * **Clustering Columns:** The remaining parts of the Primary Key. They determine how the data is sorted *on that specific server*.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/table_schema.cql`.
2. **Walkthrough the Code:**
    * Show `USE store;` to ensure we are creating tables in yesterday's Keyspace.
    * Walk through `CREATE TABLE users_by_country`. Explain that our application explicitly needs to query "Give me all users in the USA".
    * Show `PRIMARY KEY ((country_code), user_id)`.
    * **Crucial Explanation:** The parentheses around `(country_code)` designate it as the Partition Key. All USA users will live on Node A. All CAN users will live on Node B. The `user_id` is the Clustering Column, meaning the users on Node A will be sorted sequentially by their ID.
3. **Execute the Script:**
    * Paste the script into `cqlsh`.
    * Run `DESCRIBE TABLE users_by_country;` to show the internal metadata.
