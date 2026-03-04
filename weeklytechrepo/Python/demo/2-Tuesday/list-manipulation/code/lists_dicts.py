# ==========================================
# 1. LISTS (Ordered, Zero-Indexed Arrays)
# ==========================================
colors = ["red", "green", "blue"]

print(f"Original list: {colors}")
print(f"First item: {colors[0]}") # Zero-indexed!

# Appending and Removing
colors.append("yellow")
colors.remove("green")
print(f"Modified list: {colors}")

# Slicing (Grabbing a subset)
# [start:stop] -> Starts at 0, stops BEFORE 2.
subset = colors[0:2] 
print(f"Sliced list: {subset}")


# ==========================================
# 2. DICTIONARIES (Key-Value Pairs)
# ==========================================
# Extremely similar to JSON structure
user = {
    "id": 101,
    "username": "jdoe",
    "is_active": True
}

print(f"\nOriginal dict: {user}")

# Accessing a value by its Key
print(f"The username is: {user['username']}")

# Adding a new Key-Value pair dynamically
user["email"] = "jdoe@example.com"
user["is_active"] = False # Updating an existing key

print(f"Updated dict: {user}")
