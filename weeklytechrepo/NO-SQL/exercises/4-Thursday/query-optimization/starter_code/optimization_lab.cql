USE store;

-- ==========================================
-- THE LEGACY PRODUCTION TABLE (DO NOT MODIFY)
-- ==========================================
CREATE TABLE IF NOT EXISTS orders (
    order_id uuid,
    customer_id int,
    order_date timestamp,
    total_amount decimal,
    
    -- The Junior Dev made this the Partition Key.
    -- This means finding all orders for customer 101 requires scanning every server node!
    PRIMARY KEY ((order_id)) 
);

-- Mock Data Insertion
INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 101, '2026-10-14 10:00:00', 45.99);

INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 101, '2026-11-05 14:30:00', 12.50);

INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (uuid(), 202, '2026-12-01 09:15:00', 99.00);


-- ==========================================
-- YOUR SOLUTION (THE MATERIALIZED VIEW)
-- ==========================================
-- TODO: Write the CREATE MATERIALIZED VIEW statement named `orders_by_customer`.
-- Requirements:
-- 1. Partition by customer_id
-- 2. Cluster first by order_date
-- 3. Cluster second by order_id


-- ==========================================
-- PROVE YOUR SOLUTION WORKS
-- ==========================================
-- TODO: Write a SELECT statement querying your new view for customer 101's history.
