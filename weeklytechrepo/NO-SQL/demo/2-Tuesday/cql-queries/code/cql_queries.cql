USE store;

-- ==========================================
-- 1. INSERTING DATA
-- ==========================================
-- The uuid() function dynamically generates a universally unique identifier.
INSERT INTO users_by_country (country_code, user_id, first_name, last_name, email)
VALUES ('US', uuid(), 'Alice', 'Smith', 'alice@company.com');

INSERT INTO users_by_country (country_code, user_id, first_name, last_name, email)
VALUES ('US', uuid(), 'Bob', 'Jones', 'bob@company.com');

INSERT INTO users_by_country (country_code, user_id, first_name, last_name, email)
VALUES ('CA', uuid(), 'Charlie', 'Brown', 'charlie@company.com');


-- ==========================================
-- 2. A VALID SELECT QUERY
-- ==========================================
-- This query is mathematically allowed because we provided the Partition Key (country_code).
-- Cassandra knows exactly which server holds the 'US' data and goes straight there.
SELECT * FROM users_by_country WHERE country_code = 'US';


-- ==========================================
-- 3. AN INVALID SELECT QUERY (INTENTIONAL CRASH)
-- ==========================================
-- This query will fail with an InvalidRequest error.
-- Because first_name is NOT part of the Primary Key, it is not indexed across the cluster.
-- To find 'Alice', Cassandra would have to wake up every Node in the cluster and do a full table scan.
-- Cassandra strictly prevents queries that kill performance.

SELECT * FROM users_by_country WHERE first_name = 'Alice';
