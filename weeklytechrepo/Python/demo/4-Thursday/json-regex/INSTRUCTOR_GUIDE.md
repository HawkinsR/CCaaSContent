# Demo: Advanced Built-in Modules (JSON & RegEx)

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The core Python language is intentionally small. To do complex things, we must `import` modules.
2. **Regular Expressions (`re`):** Explain RegEx as a powerful, mathematical way to search strings for patterns (e.g., finding all valid email addresses in a 1,000-page document without writing 50 nested `if` statements).
3. **JSON (`json`):** Explain JavaScript Object Notation. It is the universal language of the web. Python provides a built-in library to convert Python Dictionaries to JSON Strings, and vice-versa.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/json_regex.py`.
2. **Walkthrough the Code:**
    * **RegEx:** Show the import statement `import re`.
    * Show the raw text containing emails.
    * Break down the RegEx pattern `r'[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+'`. Explain briefly what the `+` and `[ ]` brackets mean.
    * Show `re.findall(pattern, text)`. It instantly returns a clean List of matches.
    * **JSON:** Show the `import json` statement.
    * Demonstrate `json.loads(json_string)`. Explain "loads" means "Load String". It converts a String into a Python Dictionary.
    * Demonstrate `json.dumps(dictionary)`. Explain "dumps" means "Dump to String".
3. **Run the App:**
    * Execute the script. Ask the class when they might need to use these tools in the real world (e.g., calling an API, checking a user's sign-up form).
