import sqlite3

def setup_database():
    """ Creates the SQLite DB and Table """
    # TODO: Connect to company.db
    # TODO: Enable cursor
    # TODO: Create the employees table (ID, Name, Dept)
    # TODO: Return the connection and the cursor
    pass

def run_migration():
    """ Reads the CSV, cleans data, and INSERTS to SQLite """
    print("Starting Migration...")
    
    # conn, cursor = setup_database()
    
    # 1. Safely open the file
    # TODO: with open('employees.csv', 'r') as file ...
    
    # 2. Iterate through each row
    # TODO: for line in file.readlines()
        
    # 3. Clean and Insert
    # TODO: Implement a try/except ValueError block here
    # Inside the try: split the string, cast ID to int, INSERT into database
    # Inside the except: print("Skipping bad row...")
    
    
    # 4. Commit and Close
    # TODO: conn.commit() and conn.close()
    
    print("Migration Complete.")

if __name__ == "__main__":
    run_migration()
