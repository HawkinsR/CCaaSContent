# CQL Functions and JSON

## Learning Objectives

- Define and utilize basic arithmetic operators and Datetime arithmetic native to CQL.
- Describe use-cases for User-Defined Functions (UDFs).
- Perform `INSERT` and `SELECT` operations natively interacting with raw JSON strings.

## Why This Matters

For modern Contact Center applications or general Web APIs, data usually traverses networks encoded as JavaScript Object Notation (JSON). A Java application must traditionally fetch raw text columns from a database, parse them, run calculations in JVM memory, format them to JSON, and send them to the frontend. Pushing the arithmetic calculations (time durations, simple math) or JSON formatting down directly into the highly parallelized Cassandra database cluster saves immense network bandwidth and JVM computing power.

## The Concept

### Native Functions and Arithmetic

Cassandra 4.0 expanded support for basic arithmetic natively inside `SELECT` queries.
If you need to calculate an invoice total (price + tax), you can execute `SELECT price + tax AS total FROM orders`. This shifts the mathematical load horizontally onto the database cluster instead of the client machine.

Similarly, manipulating time (`datetime` math) is often necessary:

- `now()` generates a timeuuid.
- `currentDate()`, `currentTime()`, `currentTimestamp()` return respective snapshot data.

### User-Defined Functions (UDFs)

If you require complex mathematical or string manipulation logic that CQL does not natively provide, you can write custom code known as a **User-Defined Function (UDF)**.
UDFs are typically written in Java or JavaScript and executed directly inside the Cassandra node's sandbox.

*(Note: While powerful, UDFs introduce significant security and stability risks if they execute infinite loops. They are disabled by default in the `cassandra.yaml` and require explicit administrative permission to enable).*

### Native JSON Support

Cassandra natively understands JSON. The engine can ingest a single raw JSON string and automatically map the JSON keys to the CQL column names implicitly.

Conversely, you can append the `JSON` keyword to a query: `SELECT JSON * FROM users`. It forces Cassandra to return the output, not as tabular columns, but encoded as one large JSON string per row. This is incredibly efficient when building REST Microservices that simply act as pass-throughs between a frontend React application and the underlying Cassandra cluster.

## Code Example

```sql
-- --- ARITHMETIC OPERATORS ---
CREATE TABLE sensor_readings (
    sensor_id uuid PRIMARY KEY,
    temperature decimal,
    calibration_offset decimal,
    reading_taken_at timestamp
);

INSERT INTO sensor_readings (sensor_id, temperature, calibration_offset) 
VALUES (uuid(), 105.5, -2.1);

-- The math is calculated horizontally inside the DB, not in Java!
SELECT temperature + calibration_offset AS true_temperature 
FROM sensor_readings;

-- --- DATETIME FUNCTIONS ---
-- Utilize native methods to stamp the exact ingestion time
INSERT INTO sensor_readings (sensor_id, reading_taken_at) 
VALUES (uuid(), currentTimestamp());


-- --- JSON SUPPORT ---

-- 1. INSERTING via JSON
-- We can supply a raw JSON string. The DB parser maps the JSON keys to the internal Table Schema.
INSERT INTO sensor_readings JSON 
'{"sensor_id": "123e4567-e89b-12d3-a456-426614174000", "temperature": 98.6, "calibration_offset": 0.0}';

-- 2. SELECTING via JSON
-- Appending JSON formats the output.
SELECT JSON sensor_id, temperature 
FROM sensor_readings 
WHERE sensor_id = 123e4567-e89b-12d3-a456-426614174000;

/* 
Output:
{"sensor_id": "123e4567-e89b-12d3-a456-426614174000", "temperature": 98.6}
*/
```

## Summary

- **Arithmetic operations** (`+`, `-`, `*`, `/`) can be executed natively inside a `SELECT` statement, pushing the processing load to the database cluster.
- **UDFs** allow developers to compile custom Java/JavaScript arithmetic/string logic directly into the database engine.
- Appending the **`JSON`** keyword to `SELECT` and `INSERT` commands causes Cassandra to natively serialize and deserialize payload strings, accelerating API development.

## Additional Resources

- [Apache Cassandra Documentation: Arithmetic Operators](https://cassandra.apache.org/doc/latest/cassandra/cql/functions.html#arithmetic-operators)
- [DataStax: JSON Support in Cassandra](https://docs.datastax.com/en/cql-oss/3.3/cql/cql_using/useInsertJSON.html)
