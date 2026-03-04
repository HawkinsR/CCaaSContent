def process_new_user_registration(user_data_dict):
    """
    This is a terrible "God Function". 
    It parses, validates, saves, and emails all in one giant block.
    """
    print("--- Starting Registration ---")
    
    # 1. Validation Logic
    if "email" not in user_data_dict or "@" not in user_data_dict["email"]:
        print("Error: Invalid Email")
        return False
        
    if "age" not in user_data_dict or type(user_data_dict["age"]) != int:
        print("Error: Invalid Age")
        return False
        
    if user_data_dict["age"] < 18:
        print("Error: User is a minor.")
        return False

    # 2. Database Formatting Logic
    first_name = user_data_dict.get("first_name", "Unknown").capitalize()
    last_name = user_data_dict.get("last_name", "Unknown").capitalize()
    formatted_email = user_data_dict["email"].lower().strip()
    
    # 3. Simulated Database Save
    print(f"Connecting to database...")
    print(f"Executing: INSERT INTO users (fname, lname, email) VALUES ('{first_name}', '{last_name}', '{formatted_email}')")
    print(f"Database save successful.")
    
    # 4. Email Sending Logic
    print(f"Connecting to SMTP server...")
    print(f"Sending welcome email to '{formatted_email}'...")
    print(f"Subject: Welcome {first_name}!")
    
    return True

# --- Simulation ---
if __name__ == "__main__":
    good_user = {"email": "  JOHN.doe@example.com ", "age": 25, "first_name": "john", "last_name": "doe"}
    process_new_user_registration(good_user)
