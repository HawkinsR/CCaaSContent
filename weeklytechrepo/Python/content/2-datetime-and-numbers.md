# Datetimes, Numbers, and NoneType

## Learning Objectives

- Handle dates and times using the built-in `datetime` module.
- Manage absolute magnitudes using the builtin `abs()` and round metrics.
- Understand binary representations of data in Python (`bytes` and `bytearray`).
- Define the `NoneType` and its relevance in Python scripts.

## Why This Matters

Real-world systems generate immense amounts of temporal (time-based) data. Specifically, calculating the difference between two timestamps is necessary in almost every engineering discipline, from generating billing cycles to expiring user sessions. Furthermore, understanding how Python treats "nothingness" (None) prevents fatal runtime crashes when variables don't hold the data you expect them to.

## The Concept

### The `datetime` Module

Python does not have a built-in temporal primitive. To work with dates and times, you must import the `datetime` module.
When working with dates in software, you often need to represent a specific snapshot in time, parse strings into date objects, or calculate the duration between two events.

- **`datetime.now()`**: Returns the current local date and time.
- **`timedelta`**: An object representing a duration (the difference between two dates).

*(Note: Working meticulously with timezones requires external packages or awareness of "naive" vs. "aware" datetime objects. For foundational knowledge, we rely on local "naive" time.)*

### Number Operations

Beyond standard arithmetic, Python provides built-in utilities for advanced mathematical manipulation:

- **`abs(number)`**: Returns the absolute value of a number (distance from zero).
- **`round(number, digits)`**: Rounds a float to a specified number of decimal places.
- **The `math` module**: Must be imported for advanced calculations (square roots, pi, trigonometry).

### Binary Types

Python allows interaction with raw bytes, which is essential when saving images, interacting with raw network protocols, or performing low-level stream cryptography.

- **`bytes`**: An immutable sequence of integers in the range `0 <= x < 256`.
- **`bytearray`**: A mutable sequence of the same structure.

To convert a standard string into a binary bytes object, you must **encode** it (typically using `'utf-8'`).

### NoneType (`None`)

In Python, `None` is a special constant used to define a null value, or "no value at all". It is not the same as `0`, `False`, or an empty string `""`. It has its own datatype: `NoneType`.
A function that does not explicitly execute a `return` statement will implicitly return `None`. Checking if a variable `is None` is a critical safety check to perform before executing methods on it.

## Code Example

```python
import datetime
import math

# --- DATETIME ---
# Getting the current timestamp
now = datetime.datetime.now()
print("Current Time:", now)

# Formatting a datetime object into a specific string structure
# %Y = 4 digit year, %A = Day of the week
formatted_time = now.strftime("%A, %Y-%m-%d")
print("Formatted:", formatted_time)

# Creating a specific date to represent the past
past_event = datetime.datetime(2020, 1, 1)

# Calculating duration (returns a timedelta object)
duration = now - past_event
print("Days since 2020:", duration.days)

# --- NUMBERS ---
print("\nAbsolute Value of -42:", abs(-42))
print("Rounding 3.14159:", round(3.14159, 2)) # Rounds to two decimal places
print("Using Math Module (Pi):", math.pi)

# --- BINARY TYPES ---
# Encoding a string into utf-8 bytes
my_text = "Hidden Message"
my_bytes = my_text.encode('utf-8')
print("\nBytes Object:", my_bytes)

# --- NONETYPE ---
# Best practice identity check for None
missing_data = None
if missing_data is None:
    print("\nData is absent. Halting execution.")
```

## Summary

- Dates are managed by importing the `datetime` module. The difference between two dates produces a `timedelta`.
- `abs()` and `round()` are built-in, but advanced mathematical functions require the `math` module.
- Strings can be converted to primitive binary format via `encode('utf-8')`.
- `None` represents the intentional absence of a value (equivalent to `null` in Java).

## Additional Resources

- [Python Datetime Module Documentation](https://docs.python.org/3/library/datetime.html)
- [Real Python: Null in Python (NoneType)](https://realpython.com/null-in-python/)
