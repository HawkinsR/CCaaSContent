# Demo: Creating a Keyspace via CQLSH

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** In a traditional Relational Database (like PostgreSQL), the highest level container for tables is a `Database` or `Schema`. In Cassandra, this is called a `Keyspace`.
2. **Replication Strategies:** Unlike SQL databases, Cassandra assumes your data will be spread across multiple machines. When you create a Keyspace, you *must* explicitly tell Cassandra how many copies of the data to keep (Replication Factor).
    * `SimpleStrategy`: Used for single data centers.
    * `NetworkTopologyStrategy`: Used when deploying across multiple physical data centers (e.g., AWS US-East and AWS EU-West).

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. **Access the Shell:**
    * Execute `docker exec -it cassandra-node cqlsh` to drop into the interactive Cassandra Query Language Shell.
2. **Execute the Script:**
    * Open `code/create_keyspace.cql` but do NOT run it as a file. Type it out line-by-line in the shell.
    * Demonstrate `DESCRIBE keyspaces;` to show the default administrative keyspaces and the newly created `store`.
    * Demonstrate `USE store;` to switch context into the Keyspace.
