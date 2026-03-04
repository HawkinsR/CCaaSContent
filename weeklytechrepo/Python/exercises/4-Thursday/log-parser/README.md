# Collaborative Lab: The Log Parser

## The Scenario

Your Pair Programming team has been handed a raw, messy chunk of server text logs. Security needs you to extract every single IP Address that triggered a "404" (Not Found) error, format that data cleanly, and output it as a valid JSON string they can ingest into their dashboard.

## Deliverables (Pair Programming)

1. **Developer 1 (The Extractor):**
   - Navigate to the `starter_code` directory and open `log_parser.py`.
   - Write a Regular Expression pattern that matches a standard IPv4 address (e.g., `192.168.1.1`).
   - Iterate through the mock log file (provided as a multi-line string in the starter code).
   - *Only* if the line contains the text "404", use `re.search()` or `re.findall()` to extract the IP address.
   - Append those extracted IP addresses into a Python List.

2. **Developer 2 (The Serializer):**
   - Take the List populated by Developer 1.
   - Create a Python Dictionary. Give it a single key `"flagged_ips"` and assign the List of IPs as its value.
   - Use the `json` module to convert that Dictionary into a neatly indented JSON string.
   - Print the final JSON string to the console.

## Definition of Done

- The script uses `re` to correctly identify the 3 specific IP addresses associated with 404 errors, ignoring the 200 OK errors.
- The script uses `json` to output a syntactically perfect JSON string containing an array of those IP addresses.
