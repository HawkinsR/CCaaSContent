# Demo: The Migration Script (SQLite to Cassandra)

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The absolute hardest part of adopting Cassandra is migrating the legacy SQL data into it. You cannot simply "dump" a PostgreSQL database and "restore" it into Cassandra because the fundamental architecture and Primary Key paradigms are incompatible.
2. **The ETL Process:** Explain Extract, Transform, Load. We must write a script that connects to the old database (Extract), reshapes the data to fit the new NoSQL Query-Driven model (Transform), and inserts it into Cassandra (Load).

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/Migration.java` (using DataStax Java Driver concepts for demonstration, or Spring Data).
2. **Walkthrough the Code:**
    * Show establishing **Two Connections**: One to the legacy relational DB, one to the new Cassandra cluster.
    * Show the execution of `SELECT * FROM legacy_users` to pull all relational records into memory.
    * Explain the `for` loop. For every relational row, we execute a CQL `INSERT` into the Cassandra node.
    * *Instructor Note:* Emphasize that in the real world, this script must be highly multi-threaded and batch the inserts, otherwise migrating 10 million rows could take a week.
3. **Discussion:**
    * Ask the class: What happens if the script crashes halfway through? How do we prevent migrating the same data twice when we restart it? (Answer: Cassandra inserts are Upserts, so overwriting the same Primary Key is completely safe!).
