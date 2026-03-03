# OOP Concepts and Functions

## Learning Objectives

- Define and invoke functions, understanding parameters and return values.
- Comprehend variable scope (Global vs Local namespaces).
- Write anonymous single-line functions using `lambda`.
- Understand the core concepts of Object-Oriented Programming (OOP) via Classes and Objects in Python.

## Why This Matters

As your scripts grow past 50 lines, putting all your code in one block becomes unmaintainable. Functions allow you to encapsulate logic so it can be reused multiple times without copy-pasting code. When your application grows even larger, organizing code and data into Classes (Object-Oriented Programming) allows you to build complex software architectures mirroring real-world models. Understanding namespaces prevents critical bugs where variables unexpectedly overwrite one another.

## The Concept

### Functions

A function is a block of code that only runs when it is called. You can pass data, known as **parameters** (or arguments), into a function. A function can return data as a result using the `return` keyword.

Functions in Python are defined using the `def` keyword, followed by the function name, parentheses, and a colon.

### Namespaces and Scope

A namespace is a system Python uses to ensure that all names (variables, functions, classes) in a program are unique and can be used without conflict.

- **Local Scope:** A variable created *inside* a function belongs to the local scope of that function. It cannot be accessed outside the function.
- **Global Scope:** A variable created in the main body of the script is global. It can be read by functions, but modifying it inside a function requires the explicit `global` keyword.

### Lambda Expressions

A `lambda` function is a small, anonymous function. It can take any number of arguments but can only have one expression. The expression is evaluated and returned. They are highly efficient for passing simple logic into mapping or filtering functions without formally defining a `def` block.

### Classes and Objects (OOP Concepts)

Python is a multi-paradigm language, natively supporting Object-Oriented Programming.

- **Class:** A blueprint or template for creating objects. It defines the properties (variables) and methods (functions) that objects of that class will possess.
- **Object:** A specific instance of a class. If `Vehicle` is the class, `my_honda_civic` is the object.

To initialize an object with specific data upon creation, Python classes use a special built-in method called `__init__()` (the constructor).
**Crucially,** the first parameter of any method in a Python class must be `self`. `self` refers to the specific instance of the object calling the method (analogous to `this` in Java or C++).

## Code Example

```python
# --- FUNCTIONS AND SCOPE ---
tax_rate = 0.08  # Global Scope variable

def calculate_total(subtotal):
    """Calculates total including tax."""
    # subtotal is a Local Scope variable
    tax_amount = subtotal * tax_rate
    final_total = subtotal + tax_amount
    return final_total

# Calling the function
print(f"Total: ${calculate_total(100.00)}")
# print(tax_amount) # This would crash! tax_amount is Local to the function.

# --- LAMBDA FUNCTIONS ---
# A concise, anonymous function that squares a number
square = lambda x: x * x
print(f"Square of 5 is: {square(5)}")

# --- CLASSES AND OBJECTS ---
class Employee:
    # The Constructor method
    def __init__(self, name, department, salary):
        # Assigning parameters to the object's distinct properties
        self.name = name
        self.department = department
        self.salary = salary

    def give_raise(self, percentage):
        """A method belonging to the class."""
        self.salary += self.salary * percentage
        return self.salary

    def display_profile(self):
        print(f"Employee {self.name} works in {self.department}.")

# Instantiating two unique objects from the same Class blueprint
emp1 = Employee("Alice Cooper", "Engineering", 90000)
emp2 = Employee("Bob Smith", "Sales", 65000)

# Calling a method on a specific object
emp1.give_raise(0.10)
emp1.display_profile()
print(f"Alice's new salary is ${emp1.salary}")
```

## Summary

- **Functions (`def`)** encapsulate reusable blocks of logic and manage inputs and outputs.
- **Scope** dictates variable visibility. Local variables die when the function ends; Global variables live throughout the script's lifecycle.
- **Lambdas** are anonymous, single-line functions perfect for quick, throwaway calculations.
- **Classes** are blueprints containing both data (properties) and behavior (methods) grouped together. The `__init__` constructor initializes objects, and `self` explicitly references the current instance.

## Additional Resources

- [Python Classes Tutorial](https://docs.python.org/3/tutorial/classes.html)
- [W3Schools: Python Lambda](https://www.w3schools.com/python/python_lambda.asp)
