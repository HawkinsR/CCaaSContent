# Demo: Introduction to Python OOP (Classes)

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Dictionaries are great for holding data, but what if data needs to *do* something? What if we have an `Employee` that needs to `calculate_pay()`?
2. **Object-Oriented Programming (OOP):** Introduce the idea of tying state (Variables/Attributes) and behavior (Functions/Methods) together into a single "Blueprint" called a Class.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/classes.py`.
2. **Walkthrough the Code:**
    * Show the `class Vehicle:` definition.
    * Explain the magic `__init__` constructor method. It automatically fires the millisecond a new Object is born.
    * **The `self` Keyword:** This is the most crucial Python concept. `self` in Python is identical to `this` in Java. Explain why every single instance method *must* accept `self` as its very first parameter.
    * Show how `self.make` assigns the variable permanently to the specific object.
3. **Run the App:**
    * Demonstrate instantiating two different `Vehicle` objects.
    * Call the `.start_engine()` method on both and see the independent console outputs.
