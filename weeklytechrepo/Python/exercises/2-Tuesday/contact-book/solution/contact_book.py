def main():
    # 1. Create a dictionary and hardcode three employees.
    # Key = Name, Value = (Phone, Email) tuple
    contacts = {
        "John": ("555-0101", "john@company.com"),
        "Jane": ("555-0102", "jane@company.com"),
        "Bob": ("555-0103", "bob@company.com")
    }
    
    print("Welcome to the Contact Book!")
    
    # 3. Prompt user for a name
    search_name = input("Enter a name to lookup: ")
    
    # 4. Safely retrieve the tuple via .get()
    contact_info = contacts.get(search_name)
    
    if contact_info is None:
        print(f"Sorry, {search_name} was not found in the directory.")
    else:
        phone, email = contact_info # Unpack the tuple
        print(f"\n--- Contact Details ---")
        print(f"Name: {search_name}")
        print(f"Phone: {phone}")
        print(f"Email: {email}")

if __name__ == "__main__":
    main()
