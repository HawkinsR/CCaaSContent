# Control Flow and Loops

## Learning Objectives

- Use `if-elif-else` conditional logic to control script execution.
- Understand the difference between `while` loops and `for` loops.
- Iterate over collections (lists and dictionaries) using `for` loops.

## Why This Matters

A script that runs sequentially from line 1 to line 100 without deviation is rarely useful. Real-world applications require logic: "If the user is an admin, show this dashboard; otherwise, show the login screen." Alternatively, you may need to apply a tax calculation to 1,000 distinct items in a shopping cart. Mastering control flow via conditionals (`if` statements) and loops (`while` and `for`) is what gives your software the ability to make decisions and process data at a massive scale.

## The Concept

### Conditional Logic

Python uses `if`, `elif` (else if), and `else` statements for branching logic.
Remember that blocks of code are defined by indentation, not curly braces. The condition expression does not require parentheses around it.

Python will evaluate the first `if` statement. If it resolves to `True`, its block executes, and the rest of the chain is skipped. If `False`, it moves to the next `elif`. If all conditions fail, the `else` block executes.

### `while` Loops

A `while` loop executes a block of code repeatedly **as long as a condition remains True**.
These loops are powerful but dangerous; if the condition never becomes False, you will create an "infinite loop" that will crash your script or lock up your CPU. You must always ensure that the variables controlling a `while` loop are updated inside its block.

### `for` Loops

In Python, a `for` loop is primarily used for **iterating over a sequence** (like a List, Tuple, Dictionary, or String). This is different from the traditional C-style `for` loop (`for (int i=0; i<10; i++)`). Python's `for` loop behaves more like a "for-each" loop in Java.

#### Iterating Over Collections

- **Lists:** You can iterate directly through the items.
- **Dictionaries:** By default, iterating over a dictionary yields its *keys*. To get both keys and values simultaneously, use the `.items()` method.
- **The `range()` Function:** If you specifically need to loop a certain number of times (e.g., exactly 10 times) rather than iterating over an existing list, the built-in `range()` function generates a sequence of numbers on the fly.

## Code Example

```python
# --- CONDITIONALS ---
user_role = "editor"

if user_role == "admin":
    print("Welcome, Administrator. Full access granted.")
elif user_role == "editor":
    print("Welcome, Editor. Write access granted.")
else:
    print("Welcome, Guest. Read-only access.")

# --- WHILE LOOP ---
# Must update the counter inside the loop!
countdown = 3
print("\nInitiating countdown...")
while countdown > 0:
    print(countdown)
    countdown -= 1  # Decrement the counter
print("Liftoff!")


# --- FOR LOOPS ---
# Iterating over a list
frameworks = ["Django", "Flask", "FastAPI"]
print("\nPopular Python Frameworks:")
for fw in frameworks:
    print("- " + fw)

# Iterating over a specific numerical range (0 up to but not including 5)
print("\nGenerating IDs:")
for i in range(5):
    print("ID Number:", i)

# Iterating over a dictionary using .items()
stock_prices = {"AAPL": 150.25, "GOOGL": 2750.10, "MSFT": 305.50}
print("\nCurrent Market Prices:")
for ticker, price in stock_prices.items():
    print(f"{ticker} is currently trading at ${price}")
```

## Summary

- `if-elif-else` structures control branching logic based on boolean expressions.
- `while` loops run continuously as long as a condition is `True`. Beware of infinite loops.
- `for` loops iterate directly over the items in a collection (Lists, Strings).
- To loop a specific number of times, pair a `for` loop with the `range()` function.
- Iterate over Dictionary key-value pairs simultaneously using `.items()`.

## Additional Resources

- [Python Control Flow Tools (Tutorial)](https://docs.python.org/3/tutorial/controlflow.html)
- [Real Python: Conditional Statements](https://realpython.com/python-conditional-statements/)
