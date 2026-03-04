USE store;

-- 1. Table Creation and Modeling
CREATE TABLE IF NOT EXISTS temperature_by_sensor (
    sensor_id uuid,
    recorded_at timestamp,
    temperature_celsius float,
    
    -- The Partition Key (sensor_id) assigns the data to a specific Server Node.
    -- The Clustering Column (recorded_at) sorts it physically on the hard drive.
    PRIMARY KEY ((sensor_id), recorded_at)

-- This ensures the disk physically writes the newest data at the "top" of the partition.
-- Fast disk reads!
) WITH CLUSTERING ORDER BY (recorded_at DESC);


-- 2. Data Insertion
-- We will hardcode a mock UUID for testing. 
-- In production, the IoT device generates this.
INSERT INTO temperature_by_sensor (sensor_id, recorded_at, temperature_celsius)
VALUES (123e4567-e89b-12d3-a456-426614174000, '2026-10-14 10:00:00', 4.5);

INSERT INTO temperature_by_sensor (sensor_id, recorded_at, temperature_celsius)
VALUES (123e4567-e89b-12d3-a456-426614174000, '2026-10-14 10:05:00', 5.2);


-- 3. Querying the Model
-- This is ridiculously fast because we provided the Partition Key exactly.
-- Furthermore, because of our CLUSTERING ORDER BY clause, we do not need to add an ORDER BY statement here.
SELECT * FROM temperature_by_sensor
WHERE sensor_id = 123e4567-e89b-12d3-a456-426614174000;
