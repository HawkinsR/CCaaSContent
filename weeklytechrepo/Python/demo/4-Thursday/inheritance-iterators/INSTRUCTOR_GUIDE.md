# Demo: Inheritance and Iterators

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** We know how to build a basic Class. But what if we have a `Dog` and a `Cat`? They both have `name`, `age`, and a `speak()` method. Writing the same code twice violates the 'Don't Repeat Yourself' (DRY) principle.
2. **Inheritance:** Explain how a Child class can inherit all the variables and functions of a Parent class.
3. **Iterators:** Briefly explain that when you use a `for` loop on a list, Python is secretly calling `__iter__()` and `__next__()` under the hood. We can build our own objects that behave this way.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/inheritance_iterators.py`.
2. **Walkthrough the Code:**
    * **Inheritance:** Show the `Animal` parent class. Then show `class Dog(Animal):`. Explain how `super().__init__()` calls the parent's constructor so we don't have to rewrite the `name` assignment.
    * **Overriding:** Show how the `Dog` provides its own unique implementation of `speak()`.
    * **Custom Iterators:** Show the `Countdown` class. Walk through the required `__iter__` (returns `self`) and `__next__` (the logic that happens every single loop iteration). Show how it raises `StopIteration` to tell the `for` loop to gracefully exit.
3. **Run the App:**
    * Execute the script and trace the output.
