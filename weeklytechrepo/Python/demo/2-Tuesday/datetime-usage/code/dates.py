# We must import these specific objects from the built-in library
from datetime import datetime, timedelta

# 1. Getting the Current Time
now = datetime.now()
print("Raw Datetime Object:", now)

# 2. Formatting the Time (String Format Time)
# %Y = 4-digit year, %m = 2-digit month, %d = 2-digit day
formatted_date = now.strftime("%Y-%m-%d %H:%M:%S")
print("Human Readable:", formatted_date)

# 3. Creating a specific Past/Future Date
new_years = datetime(2025, 1, 1)
print("New Years Day:", new_years)

# 4. Math with Time (TimeDelta)
# Imagine a library book is due in exactly 14 days.
duration = timedelta(days=14)
due_date = now + duration
print("Book is due on:", due_date.strftime("%B %d, %Y")) 
