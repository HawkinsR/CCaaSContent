# Demo: Security Auditing AI-Generated Code

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The C-Suite loves GenAI because it theoretically makes developers 50% faster. Security teams are terrified of GenAI because it makes developers 50% faster at writing structural vulnerabilities.
2. **The Flaw in the LLM:** LLMs were trained on the internet. The internet is full of bad code. For example, millions of stack-overflow posts from 2010 teach SQL Injection as "the standard way" to write a database query. The AI learns that pattern and replicates it.
3. **The Solution:** You cannot trust AI-generated code without running it through a Static Application Security Testing (SAST) tool like SonarQube, Snyk, or traditional linters during the CI pipeline.

## Phase 2: Live Demonstration

**Time:** 10 mins

1. Open `code/ai_generated_login.py`.
2. **The Setup:** Tell the class: *"I asked an AI to write a very basic sqlite3 login endpoint for me. It runs perfectly. It throws no logic errors. But it is mortally flawed."*
3. **The Vulnerability:** Point out line 14:
    `query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"`
4. **The Exploit:** Show how an attacker typing `' OR '1'='1` into the username field completely bypasses the authentication check because of string formatting interpolation.
5. **The Correction:** Explain exactly why Parameterized Queries (e.g., `execute("SELECT * FROM users WHERE username = ?", (username,))`) are the *only* acceptable way to handle database input. AI tools frequently use string interpolation because it's "shorter and mathematically prevalent in training data."
