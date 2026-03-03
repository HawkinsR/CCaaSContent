# File Handling and Exceptions

## Learning Objectives

- Open, read, write, and delete files using Python's built-in `open()` function.
- Manage file resources safely using Context Managers (`with`).
- Catch and handle runtime errors gracefully using `try-except-finally` blocks.

## Why This Matters

Real-world applications rarely exist in total isolation; they need to persist data. Before a script can analyze a CSV full of sales data, it must first successfully locate and open that file on the hard drive. However, interacting with the Operating System (OS) is risky. What if the file doesn't exist? What if the hard drive is full, or the user doesn't have read permissions? Without Exception Handling, these OS-level errors will instantly crash your Python script. Mastering File I/O (Input/Output) alongside Exception Handling ensures your programs are robust and fault-tolerant.

## The Concept

### File Handling

The core function for interacting with files is `open(filename, mode)`.
The `mode` argument specifies what you want to do with the file:

- `"r"` - **Read** (Default): Opens a file for reading, error if the file does not exist.
- `"a"` - **Append**: Opens a file for appending data to the end, creates the file if it does not exist.
- `"w"` - **Write**: Opens a file for writing, creates the file if it does not exist, and **overwrites** existing content.
- `"x"` - **Create**: Creates the specified file, returns an error if the file exists.

You can also specify whether the file should be handled as text (`"t"`, default) or binary (`"b"`, e.g., for images).

#### The Danger of `open()`

When you `open()` a file, the OS grants your Python script an exclusive "lock" on that file. If your script crashes or you forget to explicitly call `file.close()`, that lock remains, preventing any other program on the computer from modifying the file until the OS forcefully reclaims it.

#### The Context Manager (`with`)

To guarantee that a file is safely closed regardless of what happens, Python recommends using the `with` statement (a context manager). The `with` block automatically calls `.close()` when the indented block finishes executing, even if an error is thrown inside the block.

### Exception Handling (`try-except`)

When Python encounters a critical runtime error, it raises an **Exception**. If left unhandled, the script halts immediately, printing a traceback to the console.

To build resilient software, you "handle" these exceptions.

- **`try` block:** Lets you test a block of code for errors.
- **`except` block:** Lets you define what execution path the script should take if a specific error is raised in the `try` block.
- **`finally` block:** Lets you execute code regardless of the result of the `try` and `except` blocks (often used for manual cleanup not covered by context managers).

## Code Example

```python
import os

filepath = "important_data.txt"

# --- EXCEPTION HANDLING AND FILE READING ---
try:
    # Attempting to read a file that might not exist
    print("Attempting to open file...")

    # The 'with' statement guarantees the file is closed automatically
    with open(filepath, "r") as file:
        content = file.read()
        print(f"File Output: {content}")

except FileNotFoundError:
    # This block executes ONLY if the specific FileNotFoundError is raised
    print(f"CRITICAL: The file '{filepath}' was not found on the system!")

except Exception as e:
    # The base 'Exception' catches ALL other errors as a fallback
    print(f"An unexpected error occurred: {e}")

finally:
    # This block executes no matter what happened above.
    print("Execution of the file check is complete.\n")


# --- WRITING / CREATING FILES ---
# "w" mode will overwrite existing files or create a new one.
with open(filepath, "w") as file:
    file.write("First line of the new log.\n")
    print("Successfully created and wrote to the file.")

# --- APPENDING FILES ---
# "a" mode adds to the bottom without overwriting.
with open(filepath, "a") as file:
    file.write("Second line appended to the log.\n")

# --- DELETING FILES ---
# To delete a file, we must import the 'os' module.
try:
    if os.path.exists(filepath):
        os.remove(filepath)
        print(f"File '{filepath}' has been permanently deleted.")
    else:
        print("The file does not exist, cannot delete.")
except PermissionError:
    print("You lack the OS permissions required to delete this file.")
```

## Summary

- `open(filename, mode)` is the primary tool for File I/O.
- The default mode is `"r"` (Read). You must specify `"w"` (Write, overwrites) or `"a"` (Append) to modify files.
- Always use the `with open(...) as f:` context manager design pattern to guarantee system file locks are released.
- `try-except-finally` blocks are the core mechanism for preventing runtime crashes by explicitly defining how to handle specific anticipated errors like `FileNotFoundError`.

## Additional Resources

- [Python Input and Output (Tutorial)](https://docs.python.org/3/tutorial/inputoutput.html)
- [Python Errors and Exceptions](https://docs.python.org/3/tutorial/errors.html)
- [Real Python: Reading and Writing Files](https://realpython.com/read-write-files-python/)
