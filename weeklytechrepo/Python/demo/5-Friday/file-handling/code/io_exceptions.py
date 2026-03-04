import os

# ==========================================
# 1. Writing to a File
# ==========================================
print("--- Stage 1: Writing ---")

# "w" mode opens the file for Writing. It will overwrite the file if it already exists!
# The 'with' keyword creates a Context Manager. It automatically closes the file when the block ends.
with open("student_data.txt", "w") as file_writer:
    file_writer.write("John,Doe,95\n")
    file_writer.write("Jane,Smith,88\n")
    
print("Successfully generated student_data.txt")


# ==========================================
# 2. Reading from a valid File
# ==========================================
print("\n--- Stage 2: Reading ---")

# "r" mode is for read-only. We don't want to accidentally edit this data.
with open("student_data.txt", "r") as file_reader:
    lines = file_reader.readlines()
    print("Raw output:")
    for row in lines:
        print("READ:", row.strip()) # strip() removes the hidden newline \n character
        

# ==========================================
# 3. Exception Handling (The Crash Preventer)
# ==========================================
print("\n--- Stage 3: Exception Catching ---")

fake_file = "non_existent_report_2026.csv"

# Instead of letting the script crash, we 'try' the dangerous code.
try:
    print(f"Attempting to read {fake_file}...")
    with open(fake_file, "r") as bad_reader:
        data = bad_reader.read()
        
# If a specific crash happens, we 'catch' it elegantly.
except FileNotFoundError as e:
    print(f"CRITICAL ERROR CAUGHT: {e}")
    print("The IT Department has been notified.")

# The Exception Handling equivalent to the 'with' block.
# This runs 100% of the time, regardless of if the 'try' succeeded or crashed.
finally:
    print("Cleaning up database connections and exiting script...")
