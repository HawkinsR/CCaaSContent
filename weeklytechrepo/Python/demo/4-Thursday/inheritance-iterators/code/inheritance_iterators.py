# ==========================================
# 1. INHERITANCE
# ==========================================

class Animal:
    def __init__(self, name):
        self.name = name
        
    def speak(self):
        print(f"{self.name} makes a generic animal noise.")

# The Dog class inherits from Animal
class Dog(Animal):
    def __init__(self, name, breed):
        # Call the Parent's Constructor to handle 'name'
        super().__init__(name)
        self.breed = breed
        
    # Method Overriding - The Dog replaces the generic noise
    def speak(self):
        print(f"{self.name} the {self.breed} barks: WOOF!")

print("--- Inheritance Demo ---")
generic_animal = Animal("Creature")
generic_animal.speak()

fido = Dog("Fido", "Golden Retriever")
fido.speak()


# ==========================================
# 2. CUSTOM ITERATORS
# ==========================================

class Countdown:
    def __init__(self, start):
        self.current = start

    # Required: Must return the iterator object itself
    def __iter__(self):
        return self

    # Required: The logic executed on every loop iteration
    def __next__(self):
        if self.current <= 0:
            raise StopIteration # Tells the For Loop to stop
        
        # Save the value to return, then decrement the state
        val = self.current
        self.current -= 1
        return val

print("\n--- Iterator Demo ---")
# Because we implemented __iter__ and __next__, we can use this custom object in a For Loop!
timer = Countdown(5)
for number in timer:
    print(number)
print("Blastoff!")
