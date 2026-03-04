USE store;

-- ==========================================
-- 1. Generating a Tombstone Warning
-- ==========================================
CREATE TABLE IF NOT EXISTS tombstone_test (
    user_id int,
    action_id timeuuid,
    action_name text,
    PRIMARY KEY ((user_id), action_id)
);

-- TODO: Write a script or bash command that loops 100,000 times, 
-- INSERTING an action for user_id = 1, then immediately DELETING it.
-- Then, attempt to `SELECT * FROM tombstone_test WHERE user_id = 1;`


-- ==========================================
-- 2. Generating an Unpaged Warning
-- ==========================================
CREATE TABLE IF NOT EXISTS unpaged_test (
    log_id int PRIMARY KEY,
    log_message text
);

-- TODO: Insert 500 rows.
-- TODO: Execute `SELECT * FROM unpaged_test;` and observe the Docker logs.
