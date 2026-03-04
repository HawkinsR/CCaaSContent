# Demo: Native JSON Support in CQL

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The modern web speaks JSON. Often, a Frontend React app will POST a JSON payload to a Node.js or Spring Boot backend.
2. **The Cassandra Shortcut:** Instead of forcing the Application Server completely deserialize that JSON into a massive Object just to write it to the database, Cassandra inherently understands JSON. We can literally hand a raw JSON string to Cassandra.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/json_features.cql`.
2. **Walkthrough the Code:**
    * Show `INSERT INTO ... JSON`. It takes a single, raw string containing the JSON object. Remind the class that the JSON keys must exactly match the CQL columns.
    * Show `SELECT JSON * FROM ...`. This is incredibly powerful. Cassandra will automatically serialize the result set into JSON format, meaning the Backend API can just blindly pass the DB response straight back to the Frontend without processing it.
3. **Execute the Script:**
    * Paste the commands into `cqlsh` and let the class observe the `[json]` output block.
