# Lab: Setting up a 2-Node Cassandra Cluster

## The Scenario

Your company is migrating away from a monolithic PostgreSQL database to a highly available architecture. Before moving to production, Infrastructure wants you to spin up a local 2-node Cassandra cluster to prove that nodes can autodiscover each other and share the load.

## Deliverables

1. Navigate to the `starter_code` directory. Open the `docker-compose.yml` file.
2. You have been provided the configuration for `node-1`.
3. Underneath it, define `node-2`.
4. `node-2` MUST have the following environment variables configured so it knows how to join the cluster:
    - `CASSANDRA_SEEDS=node-1` (This tells node-2 who the boss is upon boot)
    - `CASSANDRA_CLUSTER_NAME=TrainingCluster`
5. Open your terminal in the directory and run `docker-compose up -d`.
6. **Wait.** Cassandra is a heavy JVM application and takes about 60 seconds to fully boot and gossip with the other node.
7. Run the Node Tool command targeting the first node: `docker exec -it node-1 nodetool status`.

## Definition of Done

- A `docker ps` run shows two distinct Cassandra containers running.
- The `nodetool status` command clearly outputs **two rows** showing two distinct IP addresses, both marked with the status `UN` (Up and Normal).

*Note: You only mapped port `9042` on `node-1`. You communicate with node-1, and Cassandra internally handles replicating data to node-2.*
