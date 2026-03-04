# Demo: Loops and Conditions (The Fibonacci Sequence)

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** We now know how to store data. We need to know how to execute logic conditionally (e.g., *only* print the user if they are active) and repetitively (e.g., execute this pay calculation 1,000 times for every user).
2. **The Syntax:** Emphasize Python's absolute reliance on **Indentation**. In Java, curly braces `{}` define the block of code inside a loop. In Python, exactly 4 spaces (or 1 tab) define the block. If you misalign a space, the program crashes with an `IndentationError`.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/fibonacci.py`.
2. **Walkthrough the Code:**
    * **The Input & `if-else`:** Show how we ask the user for a maximum number. Point out the `if limit <= 0` condition. Note the mandatory colon `:` at the end of the `if` statement.
    * **The `while` loop:** Explain the logic of the Fibonacci sequence (adding the previous two numbers together).
    * Show how the loop continues to run *while* the condition (`next_num <= limit`) remains magically True.
    * Point out the simultaneous assignment trick: `a, b = b, next_num`.
3. **Run the App:**
    * Execute the script. Ask the class for a limit (e.g., 100) and show the sequence print out.
