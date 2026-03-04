# This is a single-line comment in Python

# 1. Variable Assignment (Dynamic Typing)
# We do not declare String, int, or boolean. The interpreter figures it out.
greeting = "Hello, World!"  # String
version = 3.12              # Float
is_awesome = True           # Boolean (Note the capital T)

# 2. Output
print(greeting)
print("Python Version:", version)

# 3. Formatted Strings (f-strings)
# f-strings are the modern, standard way to inject variables into strings.
instructor_name = "Alice"
topic = "Python Fundamentals"
print(f"{instructor_name} is teaching {topic} today.")

# 4. User Input
# The input() function halts execution and waits for the user to type something and hit Enter.
user_name = input("What is your name? ")
print(f"Nice to meet you, {user_name}!")
