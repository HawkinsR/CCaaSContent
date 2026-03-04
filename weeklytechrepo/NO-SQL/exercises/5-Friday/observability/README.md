# Lab: Administrative Observability and Warnings

## The Scenario

Your SRE (Site Reliability Engineering) team has noticed degraded performance on the production Cassandra cluster. They suspect developers are writing inefficient queries that are generating massive amounts of warnings in the background logs, but the developers' API applications are silently ignoring them.

You have been asked to intentionally trigger these administrative warnings on your local Docker node and observe the system logs using `nodetool` and Docker logs to prove the theory.

## Deliverables

1. Navigate to the `starter_code` directory and open `bad_queries.cql`.
2. **Triggering an Unpaged Query Warning:**
    * By default, Cassandra will silently warn administrators if a single query attempts to pull back an absurd amount of data without pagination.
    * Create a simple test table and insert 150 rows.
    * Run `SELECT * FROM test_table;`.
3. **Triggering a Tombstone Warning:**
    * Tombstones are Cassandra's way of marking data for deletion. Creating too many tombstones without letting the compaction process clean them up destroys disk read speeds.
    * Write a script that inserts a row, immediately deletes it, and repeats this 100 times for the exact same Partition Key.
    * Attempt to query that Partition Key.
4. **Observing the Logs:**
    * Open a new terminal. Run `docker logs cassandra-node`.
    * Search the output for `WARN` level logs mentioning `tombstones` or `unpaged_queries`.

## Definition of Done

- The developer has successfully weaponized CQL to intentionally abuse the Cassandra system architecture.
* The `docker logs` output clearly shows Cassandra internally screaming about the malicious/inefficient queries.
* The developer understands *why* executing a `DELETE` actually creates *more* data on the hard drive (a Tombstone marker) temporarily.
