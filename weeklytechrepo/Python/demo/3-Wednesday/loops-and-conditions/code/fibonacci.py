# Calculating the Fibonacci Sequence up to a certain limit
print("--- Fibonacci Generator ---")
limit_str = input("Generate sequence up to what number? ")
limit = int(limit_str)

# 1. Conditional Logic
if limit <= 0:
    # Notice the indentation!
    print("Please enter a positive integer greater than 0.")
else:
    print(f"Generating sequence up to {limit}:")
    
    # 2. Variable Initialization
    a = 0
    b = 1
    
    # Print the very first number unconditionally
    print(a, end=" ") # end=" " prevents it from printing on a new line
    
    # 3. The While Loop
    # This block keeps executing as long as the condition evaluates to True
    while b <= limit:
        print(b, end=" ")
        
        # Calculate the next number in the sequence
        next_num = a + b
        
        # Shift the variables forward
        a = b
        b = next_num
        
    print("\nSequence complete!")
