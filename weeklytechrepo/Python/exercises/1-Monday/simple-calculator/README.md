# Lab: Build a Simple Calculator

## The Scenario

Your company needs a quick command-line tool to perform basic arithmetic operations. Since Python excels at rapid scripting, you've been asked to build a simple calculator.

## Deliverables

1. Navigate to the `starter_code` directory and open `calculator.py`.
2. Use `input()` to ask the user for a **First Number**.
3. Use `input()` to ask the user for a **Second Number**.
4. Use `input()` to ask the user for an **Operation** (e.g., "+", "-", "*", or "/").
5. Since `input()` captures Strings, you must **cast** the two numbers to `float` so they can handle decimals.
6. For this basic exercise, assume the user only types `+` (We will handle conditions tomorrow). Simply add the two numbers together.
7. Print out a polished f-string displaying the mathematical equation and the final formatted result. Example: `The result of 5.0 + 10.0 is 15.0`.

## Definition of Done

- The script executes top-to-bottom without throwing a `TypeError`.
- String inputs are successfully cast to floats.
- Basic addition mathematics are proven to work accurately.
- An f-string is used for final terminal output.
