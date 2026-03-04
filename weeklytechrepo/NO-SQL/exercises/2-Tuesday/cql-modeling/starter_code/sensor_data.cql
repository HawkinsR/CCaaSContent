-- 1. Create your table inside the `store` keyspace.
-- Design your Primary Key so that we can find all records for a specific sensor_id,
-- and they are automatically clustered by recorded_at descending.
-- TODO: CREATE TABLE ...


-- 2. Insert two mock data points using the exact same sensor UUID.
-- You can use `toTimestamp(now())` to dynamically inject the current time.
-- Note: Cassandra prevents you from inserting identical clustering columns instantly, 
--.so you may want to hardcode two slightly different timestamps instead!
-- TODO: INSERT INTO ...


-- 3. Write the SELECT Statement
-- The Application Dashboard asks: "Show me the latest temperatures for a specific sensor."
-- TODO: SELECT ...
