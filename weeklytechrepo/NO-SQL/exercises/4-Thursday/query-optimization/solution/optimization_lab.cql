USE store;

-- THE LEGACY PRODUCTION TABLE
CREATE TABLE IF NOT EXISTS orders (
    order_id uuid,
    customer_id int,
    order_date timestamp,
    total_amount decimal,
    PRIMARY KEY ((order_id)) 
);

INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 101, '2026-10-14 10:00:00', 45.99);
INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 101, '2026-11-05 14:30:00', 12.50);
INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 202, '2026-12-01 09:15:00', 99.00);


-- ==========================================
-- THE MATERIALIZED VIEW FIX
-- ==========================================
-- We duplicate the table, but completely restructure the Primary Key for performance.
CREATE MATERIALIZED VIEW IF NOT EXISTS orders_by_customer AS
    SELECT customer_id, order_date, order_id, total_amount
    FROM orders
    
    -- Ensuring no components of the new Primary Key are null
    WHERE customer_id IS NOT NULL 
      AND order_date IS NOT NULL 
      AND order_id IS NOT NULL
      
    -- The New Structure:
    -- 1. `customer_id` is now the Partition Key, grouping same-customer orders onto one server.
    -- 2. `order_date` sorts them chronologically on the disk.
    -- 3. `order_id` ensures that if a customer makes two orders at the exact same millisecond, they don't overwrite.
    PRIMARY KEY ((customer_id), order_date, order_id);


-- ==========================================
-- THE OPTIMIZED QUERY
-- ==========================================
-- This now routes instantly to the correct Node holding Customer 101's partition! No table scan needed.
SELECT * FROM orders_by_customer WHERE customer_id = 101;
