-- Make sure we are in our keyspace from yesterday
USE store;

-- Cassandra Data Modeling principle: Model around the query.
-- Query: "Select all users from a specific country, ordered by their ID."

CREATE TABLE IF NOT EXISTS users_by_country (
    country_code text,
    user_id uuid,
    first_name text,
    last_name text,
    email text,
    
    -- The Primary Key definition is the heart of Cassandra
    -- 1. `country_code` is the PARTITION KEY (determines which physical node holds the data)
    -- 2. `user_id` is the CLUSTERING COLUMN (determines how data is sorted on disk on that node)
    PRIMARY KEY ((country_code), user_id)
);

-- Verify it was created
DESCRIBE TABLE users_by_country;
