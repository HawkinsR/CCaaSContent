# Demo: Persistent Data with SQLite

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The text files from the previous lesson are great for simple persistence, but terrible for querying. If we need to find "All students with a grade over 90," a `.txt` file is useless. We need a true Database natively supported by Python.
2. **SQLite:** Explain that `sqlite3` ships with Python automatically. There is no server to install or port to configure. It creates a `.db` file right in the project folder and translates raw SQL commands directly into File I/O.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/sql_demo.py`.
2. **Walkthrough the Code:**
    * Show `import sqlite3`.
    * Explain the `sqlite3.connect('local_database.db')`. Note that if the `.db` file doesn't exist, Python instantly creates it.
    * Demonstrate executing raw DDL SQL to `CREATE TABLE IF NOT EXISTS users`.
    * Demonstrate sending DML `INSERT INTO` statements. Highlight the `.commit()` command—remind them that saving the transaction is critical.
    * Show how to `.execute()` a `SELECT * FROM users` query. Introduce `.fetchall()`, which converts the SQL records natively back into a Python List of Tuples that we can loop over.
3. **Run the App:**
    * Execute the script. Point out the newly spawned `.db` file.
    * Execute the script *again*. The table creation will be ignored, but the insertion will add duplicate users. Run a quick `SELECT COUNT(*)` concept.
