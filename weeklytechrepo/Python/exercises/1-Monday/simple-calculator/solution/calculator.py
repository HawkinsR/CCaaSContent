def main():
    print("Welcome to the Simple Python Calculator!")
    
    # 1. Ask the User for two numbers
    num1_str = input("Enter your first number: ")
    num2_str = input("Enter your second number: ")
    
    # 2. Ask the user for the operator (+, -, *, /)
    operator = input("Enter your operator: ")
    
    # 3. Cast Strings to Floats
    num1 = float(num1_str)
    num2 = float(num2_str)
    
    # 4. Perform the calculation
    result = num1 + num2
    
    # 5. Output the result using a formatted string
    print(f"\nThe result of {num1} {operator} {num2} is {result}")

if __name__ == "__main__":
    main()
