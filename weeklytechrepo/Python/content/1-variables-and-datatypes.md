# Variables and Datatypes

## Learning Objectives

- Define variables and understand Python's dynamic typing.
- Identify fundamental datatypes: Strings, Numbers (Integers/Floats), and Booleans.
- Utilize casting to convert between datatypes.
- Perform basic arithmetic operations and accept user input.

## Why This Matters

Variables and datatypes are the atomic building blocks of any program. You cannot write a meaningful script without storing data in memory, manipulating it, and eventually returning a result. Understanding how Python handles data types—specifically its dynamically typed nature—prevents runtime errors and frustrating debugging sessions when a string is accidentally treated as a number.

## The Concept

### Variables and Dynamic Typing

Variables are containers for storing data values. Unlike Java, Python has no command for explicitly declaring a variable or its type. A variable is created the moment you first assign a value to it using the `=` operator.

Python is **dynamically typed**, which means you do not have to declare the type of a variable, and a variable can change type after it has been set.

### Fundamental Datatypes

1. **Numbers:**
    - `int`: Whole numbers (e.g., `10`, `-5`).
    - `float`: Numbers containing a decimal point (e.g., `3.14`, `-0.01`).
2. **Strings (`str`):** Text data written with either single `'` or double `"` quotes.
3. **Booleans (`bool`):** Represents truth values: `True` or `False`. (Note the capital letters).

### Operators

Python supports standard arithmetic operators:

- `+` (Addition), `-` (Subtraction), `*` (Multiplication), `/` (Division - always returns a float).
- `//` (Floor Division - rounds down to the nearest integer).
- `%` (Modulus - returns the remainder of the division).
- `**` (Exponentiation - e.g., `2 ** 3` is 8).

### User Input and Casting

To get text input from a user running your script via the console, use the `input()` function.
**Crucial Rule:** The `input()` function *always* returns a String, even if the user typed a number.

If you need to perform math on user input, you must **cast** (convert) that String into an Integer or Float using the `int()` or `float()` constructor functions. You can also cast numbers back to strings using `str()`.

## Code Example

```python
# Variables and Dynamic Typing
age = 25          # Initially an int
print(type(age))  # Outputs: <class 'int'>

age = "Twenty-Five" # Now it's a string!
print(type(age))  # Outputs: <class 'str'>

# Arithmetic and Booleans
x = 10
y = 3
result = x // y   # Floor division
is_greater = x > y

print("Floor Division Result:", result) # Outputs 3
print("Is X greater?", is_greater)     # Outputs True

# User Input and Casting
# input() halts the program and waits for the user to hit Enter
user_age_str = input("Please enter your age: ") 

# Cast the string to an integer to do math
user_age_int = int(user_age_str)

# Calculate years until retirement
years_left = 65 - user_age_int

# Cast the integer back to a string to concatenate it with text
print("You have " + str(years_left) + " years until retirement.")
```

## Summary

- Variables are created upon assignment; no type declaration is needed.
- Python is dynamically typed (types can change at runtime).
- Math operations are standard, with `//`, `%`, and `**` providing floor division, remainder, and exponents.
- `input()` always returns a string; use `int()`, `float()`, and `str()` to cast between types safely.

## Additional Resources

- [W3Schools: Python Variables](https://www.w3schools.com/python/python_variables.asp)
- [Python Built-in Types](https://docs.python.org/3/library/stdtypes.html)
