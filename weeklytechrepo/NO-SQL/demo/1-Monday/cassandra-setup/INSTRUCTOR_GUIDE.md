# Demo: Spinning up Apache Cassandra

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Cassandra is notoriously difficult to install locally because it requires a specific version of Java, Python, and careful configuration of system paths.
2. **The Solution (Docker):** Explain that Docker is the industry standard for running distributed systems locally. We can download a pre-configured image of Cassandra and run it inside an isolated container in seconds.
3. **Ports:** Reiterate that Cassandra communicates with applications via port `9042`.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/docker-compose.yml`.
2. **Walkthrough the Code:**
    * Show `image: cassandra:latest`. Explain this pulls the official image from Docker Hub.
    * Show the ports mapping `9042:9042` (external to internal).
3. **Run the Node:**
    * Open a terminal in the `code` directory.
    * Execute `docker-compose up -d`. (The `-d` runs it in the background so you don't lose your terminal).
    * Run `docker ps` to prove it is running.
    * Demonstrate node health using `docker exec -it cassandra-node nodetool status`. Explain the `UN` output (Up and Normal).
