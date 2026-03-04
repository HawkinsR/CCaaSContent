USE store;

CREATE TABLE IF NOT EXISTS tombstone_test (
    user_id int,
    action_id timeuuid,
    action_name text,
    PRIMARY KEY ((user_id), action_id)
);

-- SOLUTION: Since CQL does not have `for` loops, you can copy/paste this block repeatedly,
-- or use a Python/Bash script to hammer the database for you.
INSERT INTO tombstone_test (user_id, action_id, action_name) VALUES (1, now(), 'Login');
DELETE FROM tombstone_test WHERE user_id = 1;
-- (Repeat x 100,000)

-- This SELECT statement forces Cassandra to read through 100,000 "deleted" markers (Tombstones)
-- just to prove to you that there is no data left. This crashes performance.
-- Look at `docker logs cassandra-node` to see the warning!
SELECT * FROM tombstone_test WHERE user_id = 1;



CREATE TABLE IF NOT EXISTS unpaged_test (
    log_id int PRIMARY KEY,
    log_message text
);
-- Again, use a script to insert 10,000 records.

-- This SELECT statement asks for everything all at once without pagination.
-- Cassandra will warn the Administrator that someone is trying to blow up the JVM heap memory.
SELECT * FROM unpaged_test;
