# Data Migration Architecture

## Learning Objectives

- Analyze the architectural challenges of migrating from a Relational SQL Database to Apache Cassandra.
- Describe the ETL (Extract, Transform, Load) procedure.
- Understand the data structures required: Denormalization and Query-Driven Modeling.

## Why This Matters

Greenfield development (starting a project entirely from scratch) is extremely rare in the enterprise software world. Usually, an organization determines that their monolithic Oracle or MySQL database can no longer handle the immense web traffic they are receiving. To survive, they must migrate that historical data into a distributed wide-column store like Cassandra. Because the data models (SQL vs NoSQL) are entirely incompatible, you cannot simply "copy-paste" the data. Understanding the architectural strategy to safely migrate terabytes of critical corporate data is the hallmark of a Senior Solutions Architect.

## The Concept

### The Paradigm Shift

In a relational database, data is highly normalized (scattered across many tables and tied together with Foreign Keys) to save storage space.
In Cassandra, data must be highly denormalized (duplicated across many tables) to save CPU calculation time.

The migration process is fundamentally an exercise in restructuring data from a "Storage-Optimized" format into a "Query-Optimized" format.

### The ETL Pipeline

Migrating data between disjointed systems requires an **ETL Pipeline**:

1. **Extract:** Reading the data out of the legacy RDBMS (e.g., executing a massive `SELECT * FROM ... JOIN ... JOIN ...` to flatten the relationship tree).
2. **Transform:** Modifying the raw extracted SQL data into a format Cassandra understands.
    - *Example:* If the SQL database stored a user's phone numbers in a separate `UserPhones` table, the script must parse those multiple rows and transform them into a single `set<text>` data structure for the target Cassandra table.
3. **Load:** Inserting the transformed data into the Cassandra cluster using highly parallelized, asynchronous `INSERT` statements.

### Dual-Write Migration Strategy (The Strangler Fig)

You cannot simply turn off a major bank's SQL database on Friday night, run a 48-hour data migration script over the weekend, and turn on the new Cassandra database on Monday morning. The business cannot afford 48 hours of downtime.

Migrations are performed live using the **Dual-Write Pattern**:

1. **Update the Application Code:** The Spring Boot API is modified so that every new `POST`/`PUT` request simultaneously writes the data to the old SQL database AND the new Cassandra cluster.
2. **Backfill the Historical Data:** An automated ETL script is run in the background. It reads data from 10 years ago in the SQL database, transforms it, and bulk-inserts it into Cassandra. This can safely take weeks to finish.
3. **Read Verification:** The application reads data from both databases and compares them. If the Cassandra data perfectly matches the SQL data for an extended period, the teams proceed.
4. **The Cutover:** The Spring Boot API is updated to permanently stop writing to or reading from the old SQL database. The SQL database is decommissioned.

## Summary

- Migrating to Cassandra requires transforming highly normalized SQL data into denormalized, query-driven NoSQL structures and Collections.
- **ETL (Extract, Transform, Load)** pipelines automate this complex reshaping of the data tier.
- Zero-downtime migrations utilize the **Dual-Write Strategy**, wherein new data is written to both systems simultaneously while a background script slowly backfills the historical archives.

## Additional Resources

- [DataStax: RDBMS to NoSQL Data Modeling](https://www.datastax.com/learn/cassandra-architecture/rdbms-to-cassandra-data-modeling)
- [AWS: Relational to NoSQL Migration Strategies](https://aws.amazon.com/blogs/database/migration-strategy-for-relational-databases-to-amazon-dynamodb/)
