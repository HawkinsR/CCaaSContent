import sqlite3

# 1. Establish the Connection
# This creates a file named 'school.db' in the current folder if one does not exist.
# It behaves exactly like a connection to a remote PostgreSQL or Oracle server.
conn = sqlite3.connect('school.db')

# 2. Open a Cursor
# The cursor is the object we use to actually send the raw string commands to the DB engine.
cursor = conn.cursor()

# 3. Data Definition Language (DDL)
cursor.execute('''
    CREATE TABLE IF NOT EXISTS students (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        first_name TEXT NOT NULL,
        last_name TEXT NOT NULL,
        grade INTEGER
    )
''')

# 4. Data Manipulation Language (DML) - Inserting Data
# Notice the ? placeholders. This prevents SQL Injection attacks!
cursor.execute("INSERT INTO students (first_name, last_name, grade) VALUES (?, ?, ?)", ("Alice", "Williams", 98))
cursor.execute("INSERT INTO students (first_name, last_name, grade) VALUES (?, ?, ?)", ("Bob", "Johnson", 72))

# IMPORTANT: You must commit the transaction, or the data vanishes when the script ends.
conn.commit()
print("Successfully inserted 2 students into the database.")

# 5. Data Query Language (DQL) - Reading Data
# Send the query
cursor.execute("SELECT first_name, grade FROM students WHERE grade > 80")

# Fetch all the results. It returns a List of Tuples!
# e.g., [('Alice', 98), ('Charlie', 85)]
top_students = cursor.fetchall()

print("\n--- Honor Roll Students ---")
for student in top_students:
    # student[0] is the first_name, student[1] is the grade
    print(f"Name: {student[0]} | Grade: {student[1]}")

# 6. Always Close the Connection!
conn.close()
print("\nDatabase connection closed.")
