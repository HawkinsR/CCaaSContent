# Capstone Lab: The Data Migrator

## The Scenario

Your company recently acquired a smaller competitor. You've been emailed a raw CSV (Comma Separated Values) file containing their employee data, but the HR intern who typed it manually made several formatting mistakes. Some lines are missing commas, and some lines contain alphabetical letters where a numerical Employee ID should be.

Your job as a Data Engineer is to write a Python script that reads this corrupted CSV file, uses `try-except` blocks to safely ignore the malformed lines, and inserts the valid employees into a clean SQLite database.

## Deliverables

1. Navigate to the `starter_code` directory. Open the `employees.csv` file to verify its structure.
2. Open `migrator.py`.
3. Use a `with open(...)` block to read the CSV file. Read each line individually.
4. Establish a connection to an SQLite database named `company.db`.
5. Create a table named `employees` with columns: `id` (INTEGER), `name` (TEXT), `department` (TEXT).
6. **The Migration Loop:**
   - Loop over the lines you read from the file.
   - Separate the columns by splitting the string on the comma character: `line.split(",")`.
   - Wrap your parsing in a `try-except ValueError:` block.
   - Attempt to cast the first column (the ID) to an `int`. If the intern typed "ABC" instead of "101", Python will throw a `ValueError`. The `except` block should catch this and print: `Skipping corrupted row`.
   - If the line is perfectly valid, `INSERT` the row into your SQLite database.
7. **The Linting requirement:** You must run `pylint migrator.py` in your terminal and achieve a perfect score of 10.0/10.

## Definition of Done

- A local `company.db` is successfully generated.
- The `employees` table contains only the mathematically valid rows from the CSV file.
- The script does not crash when encountering malformed data.
- The Python code passes `pylint` analysis with a high score.
