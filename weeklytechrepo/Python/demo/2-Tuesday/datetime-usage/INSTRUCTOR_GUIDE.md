# Demo: The Datetime Module

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Dealing with time is notoriously difficult in programming due to leap years, time zones, and daylight saving time.
2. **The Solution:** Python's built-in `datetime` library abstracts this mathematical nightmare away from us.
3. **Key Objects:** Introduce `datetime` (a specific point in time) and `timedelta` (a duration of time, like "5 days").

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/dates.py`.
2. **Walkthrough the Code:**
    * Show the import statement: `from datetime import datetime, timedelta`. This introduces the concept that some Python tools aren't loaded by default to save memory.
    * Show `datetime.now()`.
    * Show how to format a date into a human-readable string using `.strftime()` (String Format Time).
    * Show `timedelta`. Demonstrate calculating a future date by doing straightforward math: `now + duration`.
3. **Run the App:**
    * Execute the script and show the outputs.
