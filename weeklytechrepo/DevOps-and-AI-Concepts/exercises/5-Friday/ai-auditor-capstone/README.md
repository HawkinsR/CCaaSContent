# Lab: AI Code Review Capstone

## The Scenario

You are a Senior Security Engineer. A junior developer has submitted a Pull Request (PR) containing a brand new User Registration flow. The junior developer admits they used an LLM to generate 90% of the code.

You must formally review `PullRequest.java` and reject it.

## The Flaws

The AI generated code that technically compiles, but contains severe enterprise violations. There are at least three major categories of errors hidden across the files:

1. **Security Vulnerability:** Look closely at how data is interacting with the database.
2. **Privacy/Compliance Violation:** Look at what is being logged to the console/server logs.
3. **Hardcoded Secrets:** Look for sensitive infrastructure data directly embedded in the file.

## Deliverables

1. Navigate to the `starter_code` directory and open `PullRequest.java`.
2. Create a file named `Code_Review.md`.
3. Read the Java code line by line.
4. Document every single violation you find. For each violation:
    * State the Line Number or Method Name.
    * Explain *why* it is dangerous or bad practice.
    * Provide the pseudo-code for how it should be fixed.

## Definition of Done

- A `Code_Review.md` document exists identifying the SQL Injection, the logging of raw passwords/PII, and the hardcoded database credentials.
