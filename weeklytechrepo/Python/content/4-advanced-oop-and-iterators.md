# Advanced OOP and Iterators

## Learning Objectives

- Implement Class Inheritance to eliminate code duplication.
- Override parent methods in a child class.
- Understand how Iterators work natively in Python and how to build custom iterable objects using `__iter__()` and `__next__()`.

## Why This Matters

As you build more complex enterprise systems, you will find yourself defining entities that share 90% of their logic. For example, a `Manager` class and an `Engineer` class might both need a `calculate_paycheck()` method. Writing the same method twice violates the DRY (Don't Repeat Yourself) principle. Inheritance solves this. Furthermore, understanding Iterators unlocks the ability to build massive custom data streams that do not load the entire dataset into memory at once, preventing "Out of Memory" crashes when processing large log files.

## The Concept

### Class Inheritance

Inheritance allows us to define a class that inherits all the methods and properties from another class.

- **Parent class** (Base class): The original class being inherited from.
- **Child class** (Derived class): The new class that inherits from the Parent.

A Child class can:

1. Use the inherited methods directly.
2. Add its own unique new methods.
3. **Override** an inherited method by redefining it with the same name.

To inherit, pass the Parent class as a parameter into the Child class definition: `class ChildClass(ParentClass):`

The built-in `super()` function creates a temporary object of the superclass. Calling `super().__init__()` inside a Child class's constructor ensures the child inherits all properties correctly initialized by the parent.

### Iterators

In Python, an iterator is an object that contains a countable number of values and can be traversed through (iterated upon) one value at a time.
Lists, tuples, dictionaries, and sets are all **iterable** objects. They are iterable *containers* from which you can get an iterator.

Under the hood, when you write `for item in my_list:`, Python does two things:

1. Calls `iter(my_list)` to create an iterator object.
2. Repeatedly calls `next(iterator)` to get the next item until a `StopIteration` exception is implicitly raised and caught.

#### Building Custom Iterators

To create your own object/class as an iterator, you must implement the Methods `__iter__()` and `__next__()` in your class definition.

- `__iter__()` initializes and returns the iterator object itself.
- `__next__()` returns the next sequence element or raises `StopIteration` to terminate the loop.

## Code Example

```python
# --- INHERITANCE ---
class Employee:
    """The Parent or Base Class"""
    def __init__(self, name, base_salary):
        self.name = name
        self.base_salary = base_salary

    def status_report(self):
        print(f"Employee {self.name} is clocked in.")

class Manager(Employee):
    """The Child Class inheriting from Employee"""
    def __init__(self, name, base_salary, team_size):
        # We invoke the Parent's constructor to handle the shared Logic
        super().__init__(name, base_salary)
        
        # We add the new property unique only to Managers
        self.team_size = team_size

    # Overriding the Parent's method with custom logic
    def status_report(self):
        print(f"Manager {self.name} is leading a team of {self.team_size} members.")

# Testing Inheritance
emp = Employee("Jim Halpert", 60000)
mgr = Manager("Michael Scott", 80000, 10)

emp.status_report() # Output: Employee Jim Halpert is clocked in.
mgr.status_report() # Output: Manager Michael Scott is leading a team of 10 members.

# --- CUSTOM ITERATORS ---
class EvenNumberGenerator:
    """A Class designed to generate N even numbers one at a time."""
    def __init__(self, limit):
        self.limit = limit
        self.current = 0
        
    def __iter__(self):
        # Return the object itself as the iterator initialization
        return self
        
    def __next__(self):
        # This method is called automatically on every loop iteration
        if self.current < self.limit:
            num = self.current
            self.current += 2
            return num
        else:
            # Tell the for loop we are out of numbers
            raise StopIteration

print("\nCustom Iterator Output:")
# We can now use our custom object natively in a standard for loop!
even_stream = EvenNumberGenerator(8)
for n in even_stream:
    print(n) # Output: 0, 2, 4, 6
```

## Summary

- **Inheritance** allows child classes to absorb the properties and methods of parent classes, reducing code duplication.
- The `super()` function references the immediate parent class, usually used inside `__init__()`.
- Child classes can **override** parent methods to provide specific implementations.
- **Iterators** are objects that implement `__iter__()` and `__next__()`. They form the backend mechanism for Python's `for` loops.

## Additional Resources

- [Python Classes & Inheritance](https://docs.python.org/3/tutorial/classes.html#inheritance)
- [W3Schools: Python Iterators](https://www.w3schools.com/python/python_iterators.asp)
