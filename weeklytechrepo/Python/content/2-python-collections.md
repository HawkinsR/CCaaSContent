# Python Collections

## Learning Objectives

- Compare and contrast Python's four built-in data structure types: Lists, Tuples, Sets, and Dictionaries.
- Understand mutability vs immutability.
- Utilize zero-indexing, slicing, and common collection methods.

## Why This Matters

Real-world applications rarely handle single variables in isolation. Whether you are querying a database for 100 customer records or reading a CSV file containing 10,000 log entries, you must store that data efficiently in memory. Choosing the correct collection type—a List when order matters, a Set when uniqueness matters, or a Dictionary when retrieving data by a specific key matters—is the difference between an elegant, lightning-fast script and a slow, buggy mess.

## The Concept

Python provides four primary built-in collection datatypes, each with distinct characteristics regarding mutability (the ability to change after creation), ordering, and allowed duplicates.

### 1. Lists (`list`)

A List is a collection which is **ordered**, **mutable** (changeable), and **allows duplicate members**. Lists are defined using square brackets `[]`.

Because lists are ordered, their elements are indexed.
- **Zero-Indexing:** The first element in a Python list is at index `0`. The second is at `1`.
- **Negative Indexing:** Python supports negative indexing. Index `-1` refers to the *last* element, `-2` the second to last, and so on.
- **Slicing:** You can extract a sub-list (a "slice") using a colon `:`. For example, `my_list[1:4]` returns elements from index 1 up to (but not including) index 4.

### 2. Tuples (`tuple`)

A Tuple is a collection which is **ordered** but **immutable** (unchangeable). It **allows duplicate members**. Tuples are defined using parentheses `()`.

Once a tuple is created, you cannot add, remove, or change its elements. They are faster than lists and provide a guarantee that the data will not be accidentally modified during the execution of your program.

### 3. Sets (`set`)

A Set is a collection which is **unordered** and **unindexed**. Crucially, it **does not allow duplicate members**. Sets are defined using curly braces `{}`.

Sets are highly optimized for checking if an item exists within them (membership testing). If you need to remove thousands of duplicates from a massive sequence of data, converting it to a Set is the fastest way.

### 4. Dictionaries (`dict`)

A Dictionary is a collection of **Key-Value pairs**. It is **ordered** (as of Python 3.7), **mutable**, but **does not allow duplicate keys**. Dictionaries are defined using curly braces `{}` with a colon separating the key from the value.

Instead of accessing elements by a numerical index (like a list), you access the *value* by requesting its unique *key*.

## Code Example

```python
# --- LISTS ---
fruits_list = ["apple", "banana", "cherry", "apple"]
fruits_list.append("date")         # Mutable: adding an item
fruits_list[1] = "blueberry"       # Mutable: replacing 'banana'
print("List Slicing:", fruits_list[1:3]) # Output: ['blueberry', 'cherry']

# --- TUPLES ---
# Ordered, allows duplicates, but immutable.
coords_tuple = (40.7128, -74.0060, 40.7128)
# coords_tuple[0] = 41.0  <-- This would throw a TypeError!

# --- SETS ---
# Unordered, NO duplicates.
unique_tags = {"python", "coding", "java", "python"}
print("\nSet:", unique_tags) # Output: {'python', 'java', 'coding'}
unique_tags.add("cloud")     # Mutable addition

# --- DICTIONARIES ---
# Key-Value pairs. Keys must be unique.
student_dict = {
    "name": "Jane Doe",
    "age": 22,
    "major": "Computer Science"
}
# Accessing a value by its key
print("\nStudent Name:", student_dict["name"])

# Updating a value
student_dict["age"] = 23

# Adding a new key-value pair
student_dict["graduated"] = False
```

## Summary

- **Lists (`[]`):** Ordered, indexed (starting at 0), mutable, allows duplicates. Best general-purpose collection.
- **Tuples (`()`):** Ordered, indexed, immutable, allows duplicates. Best for static data.
- **Sets (`{}`):** Unordered, unindexed, mutable, no duplicates. Best for finding unique items.
- **Dictionaries (`{key: value}`):** Ordered, mutable, no duplicate keys. Best for mapping specific attributes to values.

## Additional Resources

- [Python Data Structures Tutorial](https://docs.python.org/3/tutorial/datastructures.html)
- [Real Python: Lists and Tuples](https://realpython.com/python-lists-tuples/)
