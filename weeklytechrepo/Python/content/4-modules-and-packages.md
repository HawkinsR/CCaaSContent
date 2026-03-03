# Python Modules, Packages, and `pip`

## Learning Objectives

- Define what a Module is and learn how to `import` it.
- Differentiate between a Module and a Package.
- Utilize highly common built-in modules: `math`, `logging`, `json`, and `re` (regex).
- Define `pip` and how to install third-party packages from the Python Package Index (PyPI).

## Why This Matters

"Don't reinvent the wheel." This is the foundational mantra of modern software development. Why spend three days writing complex mathematics algorithms or a JSON parser when thousands of other developers have already written, optimized, and tested that exact generic code? Modules and Packages allow you to import powerful existing code into your project instantly.

## The Concept

### What is a Module?

A module is simply a file containing Python statements and definitions (functions, classes, variables). The file name is the module name with the suffix `.py` appended.
When you write a 500-line script, it's difficult to read. If you split it into 5 files (modules) of 100 lines each based on functionality, your code becomes modular.

You access the contents of one module from another by using the `import` statement.

### What is a Package?

If a module is a single file, a package is a structured *directory* of modules. Packages are a way of structuring Python’s module namespace by using "dotted module names."

### Highly Critical Built-in Modules

Python ships with a massive "Standard Library" of built-in modules you can import out of the box without downloading anything.

1. **`math`**: Extends basic arithmetic with complex mathematical functions (trigonometry, logarithms).
2. **`logging`**: `print()` statements are terrible for production scripts. The `logging` module allows you to write formatted log messages categorized by severity (DEBUG, INFO, WARNING, ERROR, CRITICAL) to a specific log file or console.
3. **`json`**: Provides `json.dumps()` to serialize a Python dictionary into a JSON formatted string, and `json.loads()` to deserialize a JSON string back into a Python dictionary.
4. **`re`**: The Regular Expression module. Provides advanced capabilities to search strings for complex patterns (e.g., verifying if a string looks like an email address or extracting IP addresses from a log line).

### The Package Installer: `pip`

The core philosophy of Python is that its standard library is comprehensive, but the true power of Python lies in its global community.
**PyPI** (Python Package Index) is a repository containing hundreds of thousands of third-party packages (e.g., NumPy for data science, Requests for HTTP calls, Flask for web APIs).

**`pip`** is the standard package manager used to download and install packages from PyPI into your local environment.
From the command line (not the Python interpreter), you run: `pip install requests`
Once installed, your Python code can seamlessly `import requests`.

## Code Example

```python
# Importing entirely built-in standard library modules
import math
import logging
import json
import re

# --- LOGGING ---
# Configure the logger to only output warnings and errors
logging.basicConfig(level=logging.WARNING, format='%(levelname)s - %(message)s')

logging.info("This info message will NOT normally appear due to the WARNING level.")
logging.warning("This implies something unexpected happened.")
logging.error("A critical operation failed!")

# --- MATH ---
radius = 5.0
area = math.pi * math.pow(radius, 2)
print(f"\nThe area of the circle is roughly {math.ceil(area)} units.")

# --- JSON ---
# Simulating receiving a raw formatted JSON string from a Web API
raw_json_response = '{"id": 404, "target": "database", "status": "down"}'

# Deserialize JSON String -> Python Dictionary
data_dict = json.loads(raw_json_response)
print(f"\nTarget service {data_dict['target']} is {data_dict['status']}.")

# Serialize Python Dictionary -> JSON String
response_payload = {"response_code": 200, "message": "Received"}
json_string = json.dumps(response_payload)
print(f"Sending response payload: {json_string}")

# --- REGEX (re) ---
# Extracting all word characters followed by an '@' followed by more word characters
text_block = "Please reach out to admin@domain.com or contact support@domain.net immediately."

# The re.findall() returns a list of all matches found
emails = re.findall(r'[\w\.-]+@[\w\.-]+', text_block)
print("\nExtracted Emails via Regex:")
for mail in emails:
    print("- " + mail)
```

## Summary

- A **Module** is a single `.py` file you can import. A **Package** is a folder filled with modules.
- The `import` statement injects external functionality into your script's namespace.
- Important standard libraries include `math` (advanced math), `logging` (production outputs), `json` (serialization), and `re` (pattern matching).
- **`pip`** runs from your terminal and installs community packages from PyPI globally or into a virtual environment.

## Additional Resources

- [Python Standard Library Overview](https://docs.python.org/3/library/index.html)
- [PyPI - The Python Package Index](https://pypi.org/)
- [Regex101 - Useful tool for testing logic before writing `re` code](https://regex101.com/)
