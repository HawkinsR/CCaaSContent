# 1. Defining the Blueprint (The Class)
class Vehicle:
    
    # 2. The Constructor
    # This automatically runs when we create a new Vehicle.
    # 'self' refers to the specific object being created in memory right now.
    def __init__(self, make, model, year):
        # We attach the incoming parameters to the object's memory.
        self.make = make
        self.model = model
        self.year = year
        self.is_running = False
        print(f"A new {self.year} {self.make} {self.model} rolled off the assembly line!")

    # 3. An Instance Method (Behavior)
    # Again, 'self' must be the first parameter so the method knows WHICH car to start.
    def start_engine(self):
        if self.is_running:
            print(f"The {self.make} is already running.")
        else:
            self.is_running = True
            print(f"Vroom! The {self.make} engine is now ON.")

# ==========================================
# 4. Using the Blueprint (Instantiation)
# ==========================================
print("--- Dealership ---")

# We don't pass anything in for 'self'. Python handles that invisibly.
car_one = Vehicle("Toyota", "Camry", 2024)
car_two = Vehicle("Ford", "Mustang", 1969)

print("\n--- Testing Behaviors ---")
car_one.start_engine()
car_two.start_engine()
car_one.start_engine() # Trying to start it twice
