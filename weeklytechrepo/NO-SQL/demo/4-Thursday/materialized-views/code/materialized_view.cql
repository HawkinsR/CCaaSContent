USE store;

-- ==========================================
-- 1. Create the Materialized View
-- ==========================================
-- This duplicates data from the 'users_by_country' table we made on Tuesday.

CREATE MATERIALIZED VIEW IF NOT EXISTS users_by_name AS
    SELECT first_name, country_code, user_id, last_name, email
    FROM users_by_country
    -- You must prove to Cassandra that the new Primary Key will never be NULL
    WHERE first_name IS NOT NULL AND country_code IS NOT NULL AND user_id IS NOT NULL
    -- We promote 'first_name' to be the new Partition Key.
    -- We must still include the original Primary Key components (country_code, user_id) 
    -- as clustering columns to ensure row uniqueness.
    PRIMARY KEY ((first_name), country_code, user_id);

-- ==========================================
-- 2. Query the View
-- ==========================================
-- On Tuesday, this exact same mathematical query crashed.
-- Today, it routes instantly to the specific Server Node holding the 'Alice' partition.
SELECT * FROM users_by_name WHERE first_name = 'Alice';
