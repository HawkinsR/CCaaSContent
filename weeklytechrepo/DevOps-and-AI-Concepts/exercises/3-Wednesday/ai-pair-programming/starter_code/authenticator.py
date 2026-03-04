class UserAuthenticator:
    def __init__(self):
        self.users_db = {
            "alice": "password123",
            "bob": "secure_pass",
            "charlie": "admin1"
        }
        self.login_attempts = 0
        self.successful_logins = 0

    def attempt_login(self, username, password):
        self.login_attempts += 1
        
        # Bug 1: Improper Dictionary Check. 
        # This will throw a KeyError if username isn't in DB.
        if self.users_db[username] == password:
            self.successful_logins += 1
            print(f"Welcome {username}")
            return True
        else:
            print("Invalid credentials")
            return False

    def remove_weak_passwords(self):
        """Removes any user with a password less than 8 characters."""
        # Bug 2: Modifying a dictionary while iterating over it. 
        # This throws a RuntimeError in Python.
        for user, password in self.users_db.items():
            if len(password) < 8:
                del self.users_db[user]
                print(f"Removed weak user: {user}")

    def get_success_rate(self):
        """Returns the percentage of successful logins."""
        # Bug 3: Division by zero. 
        # If this is called before any login attempts, the server crashes.
        return (self.successful_logins / self.login_attempts) * 100

# Testing the broken code
if __name__ == "__main__":
    auth = UserAuthenticator()
    
    # This works
    auth.attempt_login("alice", "password123")
    
    # Bug 1 Trigger 
    # auth.attempt_login("dave", "password123") 
    
    # Bug 2 Trigger
    # auth.remove_weak_passwords()
    
    # Bug 3 Trigger (if called first)
    # print(auth.get_success_rate())
