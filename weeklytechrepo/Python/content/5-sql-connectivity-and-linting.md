# Database Connectivity and Linting

## Learning Objectives

- Connect a Python script to a relational SQL database.
- Execute basic SQL statements (DDL, DML) using standard Python database drivers.
- Prevent SQL Injection attacks using parameterized queries.
- Understand the primary purpose of `pylint` and why enforcing code standards is essential for professional teams.

## Why This Matters

While text files and CSVs are useful, enterprise applications rely on robust, relational databases to store and index massive quantities of structured data. Python must be capable of speaking directly to SQL engines (like PostgreSQL, MySQL, or SQLite) to manipulate that data. Concurrently, as you write these complex integrations, adhering to community formatting standards (`PEP 8`) using tools like `pylint` ensures your codebase remains uniform, readable, and maintainable by any other Python developer in the world.

## The Concept

### Database Connectivity (DB-API)

Python defines a standard specification for relational database access, meaning the code you write to connect to MySQL looks almost identical to the code you write to connect to PostgreSQL. You simply swap the implementation driver.

For testing and local development, the `sqlite3` driver is built directly into Python's Standard Library. SQLite is a C library that provides a lightweight disk-based database that doesn't require a separate server process.

The sequence of events for Python database interaction is standard:

1. **Connect:** Establish a connection object to the database file or remote server URL.
2. **Cursor:** Generate a `cursor` object from the connection. The cursor is the vehicle used to execute SQL commands and fetch results.
3. **Execute & Commit:** Run the SQL command using `cursor.execute()`. For DML statements (`INSERT`, `UPDATE`, `DELETE`), you must specifically call `connection.commit()` to finalize the transaction.
4. **Close:** Guarantee the connection is closed to prevent memory leaks and database resource exhaustion.

#### Parameterized Queries (Security)

Constructing SQL strings using concatenation (e.g., `"SELECT * FROM users WHERE name = '" + input_name + "'"`) is the most dangerous anti-pattern in software. It invites **SQL Injection**, allowing malicious users to execute arbitrary commands like `DROP TABLE`.

You must exclusively use parameterization via placeholders (`?` in sqlite3, or `%s` in psycopg2/Postgres), separating the SQL syntax from the user-provided data values.

### Linting with `pylint`

Python enforces specific spacing via the `PEP 8` style guide (Python Enhancement Proposal 8). `pylint` is a static code analysis tool that checks your code for errors, enforces this standard, looks for code smells (like methods that are too complex or unused variables), and offers simple refactoring suggestions.

Running `pylint my_script.py` in your terminal grades your code out of `10.0`. A score below `8.0` on a professional team will typically result in your Pull Request being rejected by the CI pipeline.

## Code Example

```python
import sqlite3

# This will create a local file named 'inventory.db' in the directory
db_name = "inventory.db"

# --- ESTABLISHING CONNECTIVITY ---
# It's best practice to use Exception Handling when connecting to databases.
try:
    # 1. Open the connection
    conn = sqlite3.connect(db_name)
    
    # 2. Instantiate the Cursor
    cursor = conn.cursor()
    print("Database connected successfully.")

    # --- EXECUTING DDL (Data Definition) ---
    # Create a table if it doesn't already exist
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS products (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            price REAL NOT NULL,
            stock INTEGER DEFAULT 0
        )
    """)

    # --- EXECUTING DML (Data Manipulation) WITH PARAMETERIZATION ---
    # Simulated User Input (Crucial: NEVER concatenate this into the SQL string!)
    new_product_name = "Mechanical Keyboard"
    new_product_price = 125.50
    new_product_stock = 45

    # Safe SQL Insert using the ? placeholder
    insert_sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)"
    # The tuple of arguments is safely mapped to the placeholders by the driver
    cursor.execute(insert_sql, (new_product_name, new_product_price, new_product_stock))

    # 3. Commit the transaction to officially save the INSERT
    conn.commit()
    print("Product inserted successfully.")

    # --- FETCHING DATA ---
    cursor.execute("SELECT id, name, price FROM products WHERE stock > ?", (10,))
    # Retrieve all rows matching the query
    available_products = cursor.fetchall()

    print("\nProducts currently in stock:")
    for product in available_products:
        # The result set rows are returned as Tuples
        print(f"ID: {product[0]} | Name: {product[1]} | Price: ${product[2]}")


except sqlite3.Error as e:
    # Rollback any uncommitted changes if an error occurs mid-transaction
    print(f"An error occurred: {e}")
    if conn:
        conn.rollback()

finally:
    # 4. Cleanup Phase: Ensure the connection is closed.
    if conn:
        conn.close()
        print("\nDatabase connection closed.")
```

## Summary

- Connect to DBs using drivers (like `sqlite3` for local files or `psycopg2` for Postgres servers) to generate a Connection and a Cursor.
- DML statements (`INSERT`/`UPDATE`) require an explicit `.commit()` to permanently save.
- Always use `try-except-finally` blocks and ensure `.close()` is called in the `finally` block to prevent resource leaks.
- Parameterized Queries (using placeholders like `?` or `%s`) are mandatory practice to prevent deadly SQL Injection vulnerabilities.
- Run `pylint` on your code before pushing to enforce `PEP 8` styling and static best practices.

## Additional Resources

- [Python SQLite3 Documentation](https://docs.python.org/3/library/sqlite3.html)
- [Pylint Official Documentation](https://pylint.readthedocs.io/en/latest/)
- [OWASP: SQL Injection Prevention](https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html)
