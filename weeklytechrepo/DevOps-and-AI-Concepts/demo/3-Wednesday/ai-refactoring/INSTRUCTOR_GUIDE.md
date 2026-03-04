# Demo: AI-Assisted Code Refactoring

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The hardest part of software engineering isn't writing new code; it's understanding and modifying old, bad code.
2. **The Monolith:** Show the class `code/bad_processor.py`. It is a classic "God Function." One single 50-line method that does parsing, validation, database simulation, and email sending all at once.
3. **The Goal:** We want to refactor this into the "Single Responsibility Principle" (SRP) where each function does exactly one thing, making it easy to unit test.

## Phase 2: Live Prompting

**Time:** 10 mins

1. Open your LLM.
2. **The Prompt:**
    *"I have a Python function that violates the Single Responsibility Principle. Please refactor this code into a class with small, private helper methods. The public method should just orchestrate the calls. Keep the exact same business logic and print statements."*
    *(Paste the contents of `bad_processor.py`)*
3. **The Review:**
    * Look at the AI's output. Notice how it likely broke it down into `_validate_user()`, `_save_to_db()`, and `_send_email()`.
    * Discuss with the class: *Why is this better?* (Answer: We can now easily write a Unit Test just for the validation logic without accidentally triggering a fake email).
