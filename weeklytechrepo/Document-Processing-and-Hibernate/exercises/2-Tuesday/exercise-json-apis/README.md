# Exercise: JSON APIs with Jackson and Gson

## Overview

**Mode:** Implementation (Code Lab)
**Time Estimate:** 2 Hours

**Scenario:** You are building a notification gateway. Systems will send incoming alerts in JSON format. You need to expose an API to receive these alerts, and you must demonstrate the ability to process them using both Jackson and Gson.

## Your Goal

Build a REST endpoint that accepts JSON input, deserializes it, modifies the data, and returns a JSON response using both Jackson and Gson libraries.

---

## Starter Code Setup

Navigate to the `/starter_code` directory. A Spring Boot project is provided with Spring Web and Gson dependencies included.

---

## Core Tasks

### Task 1: The Alert Model

1. Create an `Alert` POJO with fields: `String severity`, `String message`, and `long timestamp`.
2. The incoming JSON will look like this:

   ```json
   {
     "alert_level": "CRITICAL",
     "content": "Database CPU at 99%",
     "time_received": 1698420000
   }
   ```

3. Use **Jackson annotations** (`@JsonProperty`) to map these incoming JSON keys to your Java field names automatically.

### Task 2: The Jackson Endpoint

1. Create an `AlertController` marked as a `@RestController`.
2. Create a `POST /api/alerts/jackson` endpoint.
3. Accept the incoming JSON directly as an `Alert` object using `@RequestBody`. (Spring uses Jackson automatically).
4. **Processing:** Change the `severity` to "ACKNOWLEDGED - " + original severity.
5. Return the modified `Alert` object.

### Task 3: The Gson Endpoint

1. Create a `POST /api/alerts/gson` endpoint.
2. Accept the incoming request body as a raw `String`.
3. Manually deserialize the string into an `Alert` object using a `Gson` instance.
4. **Processing:** Change the `message` to "PROCESSED: " + original message.
5. Manually serialize the modified `Alert` back to a JSON string using Gson and return it (ensure you set the `produces` attribute of the `@PostMapping` to `application/json`).

---

## Definition of Done

- Testing `POST /api/alerts/jackson` with the sample payload returns JSON where the severity begins with "ACKNOWLEDGED".
- Testing `POST /api/alerts/gson` with the sample payload returns JSON where the content begins with "PROCESSED".
- You can successfully explain the difference between automatic framework serialization (Jackson) and manual programmatic serialization (Gson).
