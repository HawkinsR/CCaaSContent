import sqlite3

# Data Migrator Solution Code

def setup_database():
    """ Creates the SQLite DB and Table """
    conn = sqlite3.connect('company.db')
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS employees (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            department TEXT NOT NULL
        )
    ''')
    # Save the table creation
    conn.commit()
    return conn, cursor

def run_migration():
    """ Reads the CSV, cleans data, and INSERTS to SQLite """
    print("Starting Migration...")
    
    conn, cursor = setup_database()
    
    try:
        # 1. Safely open the file using Context Manager
        with open("employees.csv", "r") as file:
            for line in file.readlines():
                # 2. Split the string by Commas
                data = line.strip().split(',')
                
                # We expect exactly 3 items: ID, Name, Department
                if len(data) != 3:
                    print(f"Skipping bad row (incorrect column count): {line.strip()}")
                    continue
                    
                # 3. Clean and Insert
                try:
                    # Attempt to force the first string into an Integer
                    emp_id = int(data[0])
                    emp_name = data[1]
                    emp_dept = data[2]
                    
                    # If we survived the int() cast, the data is good.
                    cursor.execute(
                        "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)", 
                        (emp_id, emp_name, emp_dept)
                    )
                    print(f"Inserted: {emp_name}")
                    
                except ValueError:
                    # This catches rows containing alphabetical letters in the ID column
                    print(f"Skipping bad row (ValueError): {line.strip()}")

        # 4. Commit all the good rows and safely close
        conn.commit()
    except FileNotFoundError:
        print("ERROR: employees.csv was not found.")
    finally:
        conn.close()
        print("Migration Complete. Database connections closed.")

if __name__ == "__main__":
    run_migration()
