# Lab: IoT Sensor Data Modeling

## The Scenario

Your company makes smart thermometers for industrial refrigerators. Thousands of them ping a server every second with their current temperature. They have decided Postgres can no longer handle the sheer volume of writes, and have mandated a switch to Cassandra.

You have been handed the first query requirement from the application dashboard:
**Query Requirement 1:** "Give me all the temperature readouts for a specific `sensor_id`, starting from the most recent readout and working backwards."

## Deliverables

1. Navigate to the `starter_code` directory and open `sensor_data.cql`.
2. Switch to your `store` keyspace.
3. Write a `CREATE TABLE` statement named `temperature_by_sensor`. You will need:
    * `sensor_id` (uuid)
    * `recorded_at` (timestamp)
    * `temperature_celsius` (float)
4. **The Model Challenge:** Design the Primary Key.
    * Your Partition Key must allow Cassandra to find a specific sensor quickly.
    * Your Clustering Column must be the timestamp.
    * *Bonus:* Use the `WITH CLUSTERING ORDER BY (column_name DESC);` clause to ensure the data is sorted chronologically descending on the hard drive.
5. Write two `INSERT` statements providing mock data. You can use the `toTimestamp(now())` function to generate a valid timestamp.
6. Write the valid `SELECT` query that satisfies the original business requirement.

## Definition of Done

- A valid table exists in the `store` keyspace.
* The Partition Key and Clustering Column accurately align with the required query.
* The `SELECT` statement successfully outputs the mock data ordered from newest to oldest.
