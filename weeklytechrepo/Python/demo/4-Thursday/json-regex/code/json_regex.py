import re
import json

# ==========================================
# 1. Regular Expressions (RegEx)
# ==========================================
print("--- RegEx Demo ---")

messy_text = "Please contact support@company.com or sales@company.org. Do not email admin@."

# The RegEx pattern defining what a standard email looks like
# r'' means "Raw String", which prevents Python from misinterpreting the backslashes
email_pattern = r'[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+'

# re.findall instantly scans the entire string and returns a Python List of matches
found_emails = re.findall(email_pattern, messy_text)

print(f"Extracted Emails: {found_emails}")


# ==========================================
# 2. JSON Parsing
# ==========================================
print("\n--- JSON Demo ---")

# API responses usually arrive as giant Strings pretending to be JSON
raw_json_string = '{"user_id": 101, "username": "alice_dev", "is_admin": true}'

print(f"Type of raw response: {type(raw_json_string)}")

# Convert the String into a usable Python Dictionary (Load String)
parsed_dict = json.loads(raw_json_string)

print(f"Type after parsing: {type(parsed_dict)}")
print(f"Safely accessed Username: {parsed_dict.get('username')}")

# Convert a Python Dictionary back into a JSON String (Dump to String)
# indent=4 makes it pretty and readable
repacked_json = json.dumps(parsed_dict, indent=4)
print("\nRepacked JSON String:")
print(repacked_json)
