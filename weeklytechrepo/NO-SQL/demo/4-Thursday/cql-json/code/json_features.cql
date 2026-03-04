USE store;

-- ==========================================
-- 1. Inserting Raw JSON Data
-- ==========================================
-- The JSON keyword tells Cassandra to unpack this string and map the keys to the columns.
INSERT INTO users_by_country JSON '{
  "country_code": "UK",
  "user_id": "550e8400-e29b-41d4-a716-446655440000",
  "first_name": "Harry",
  "last_name": "Potter",
  "email": "h.potter@hogwarts.uk"
}';


-- ==========================================
-- 2. Reading Data as Raw JSON
-- ==========================================
-- By adding the JSON keyword to our SELECT statement, Cassandra bypasses 
-- standard row formatting and outputs valid JSON objects.

SELECT JSON * FROM users_by_country WHERE country_code = 'UK';
