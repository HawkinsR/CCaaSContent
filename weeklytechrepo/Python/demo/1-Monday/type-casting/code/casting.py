# 1. The Input Trap
# Even if they type '25', Python sees it as the text string "25".
age_string = input("Enter your age: ")

# Let's prove it's a string using the type() function
print("Before casting, the type is:", type(age_string))

# This line would CRASH the program with a TypeError:
# future_age = age_string + 5  

# 2. Explicitly Casting (String to Int)
# We cast the variable by wrapping it in int()
age_number = int(age_string)

print("After casting, the type is:", type(age_number))

# Now we can do math!
future_age = age_number + 5
print(f"In 5 years, you will be {future_age} years old.")

# 3. Casting (Int to Float)
price = 10
print(f"Price as int: {price}")
price_float = float(price)
print(f"Price as float: {price_float}")

# 4. Casting (Float to Int) - Note this truncates the decimal, it doesn't round
pi = 3.14159
pi_int = int(pi)
print(f"Pi cast to int: {pi_int}") # Outputs 3
