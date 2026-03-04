-- 1. Create the Keyspace (Schema)
-- SimpleStrategy is perfectly fine for our local 1-node Docker testing.
-- replication_factor = 1 means there is exactly 1 copy of the data. No redundancy.
CREATE KEYSPACE IF NOT EXISTS store 
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

-- 2. Switch Context into the Keyspace
USE store;

-- 3. Verify Creation
DESCRIBE keyspaces;
