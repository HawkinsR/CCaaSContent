# Code Review: PullRequest.java

## Finding 1: Hardcoded Secrets (Lines 6-8)

**Severity: Critical**
**Description:** The production database URL, username, and password are hardcoded directly into the Java source file. If this code is pushed to GitHub, our database is instantly compromised.
**Correction:** Remove these constants. These values should be injected at runtime via Environment Variables (`System.getenv("DB_PASS")`) or a secure configuration file loaded via Spring Boot.

## Finding 2: Logging Sensitive PII (Lines 13-15)

**Severity: High**
**Description:** The application is using `System.out.println` to log the user's plaintext password and Social Security Number (SSN). These logs will be written to disk on our servers, violating data privacy laws.
**Correction:** Delete these log statements entirely. Never log passwords or unmasked PII. If you must log the attempt, only log the `username`. Furthermore, `System.out.println` should be replaced with a proper logging framework (like SLF4J/Logback).

## Finding 3: SQL Injection Vulnerability (Line 21)

**Severity: Critical**
**Description:** The `INSERT` statement is being constructed using String Concatenation (`+`). This allows a malicious user to break out of the SQL syntax and inject their own commands (e.g., dropping the table).
**Correction:** Replace `Statement` with `PreparedStatement`. Use parameterized queries (`?`) so the JDBC driver safely escapes the input.

*Example Fix:*

```java
String sql = "INSERT INTO users (username, passhash, ssn) VALUES (?, ?, ?)";
PreparedStatement pstat = conn.prepareStatement(sql);
pstat.setString(1, username);
pstat.setString(2, hashedPassword); // Passwords must be hashed first!
pstat.setString(3, ssn);
pstat.executeUpdate();
```
