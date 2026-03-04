# Demo: Exploring cassandra.yaml

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Developers use CQL. Site Reliability Engineers (SREs) and Database Administrators (DBAs) use `cassandra.yaml`. It is the central configuration file that dictates how the physical server behaves.
2. **Key Configurations:** Explain that this file controls everything from heap memory limits, to cluster naming, to where the physical data files are stored on the hard drive, to how aggressive the garbage collector is.

## Phase 2: The Demonstration (Live Implementation)

**Time:** 10 mins

1. **Accessing the File:**
    * Explain that because we are running Docker, the file is hidden inside the container.
    * Execute: `docker exec -it cassandra-node /bin/bash`.
    * Once inside the container's shell, navigate to the config folder: `cd /etc/cassandra`.
2. **Exploring the File:**
    * Since we don't have a GUI inside the container, use the `cat` or `less` command: `cat cassandra.yaml`.
    * *Alternative:* You can use `grep` to find specific fields. Run `grep "cluster_name" cassandra.yaml`. Prove that the value matches the `CASSANDRA_CLUSTER_NAME` we injected via Docker Compose on Monday!
3. **Key Takeaways:**
    * Point out `num_tokens` (controls how data is distributed across the nodes).
    * Point out `data_file_directories` (where the literal `.db` files live out of sight).
    * Explain that changing *any* value in this file requires a full restart of the Cassandra JVM node (`docker restart cassandra-node`) for changes to take effect.
