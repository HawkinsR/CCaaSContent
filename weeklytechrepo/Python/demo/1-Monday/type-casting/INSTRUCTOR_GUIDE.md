# Demo: Dynamic Typing and Casting

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Remember how we proved `input()` always returns a string? What happens if we ask the user for their age, and then try to mathematically add 5 to it?
2. **The TypeError:** Python is dynamically typed (you don't declare types), but it is *strongly typed* at runtime. It will not implicitly allow you to add an integer to a string. It will throw a `TypeError`. We must explicitly manually cast it.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/casting.py`.
2. **Walkthrough the Code:**
    * Show taking numerical input from the user.
    * Point out that passing a variable into `type(var)` reveals its system data type.
    * Show how `int(variable)` explicitly forces the string into a mathematical integer.
    * Show the reverse: `str(number)` to cast an integer back to a string for concatenation if not using an f-string.
3. **Run the App:**
    * Execute the script. Let the students see the type change in the console log from `<class 'str'>` to `<class 'int'>`.
