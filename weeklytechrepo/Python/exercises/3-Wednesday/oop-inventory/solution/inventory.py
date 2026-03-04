class Item:
    # 1. Constructor
    def __init__(self, name, quantity, price):
        self.name = name
        self.quantity = quantity
        self.price = price

class InventoryManager:
    # 2. The Manager
    def __init__(self):
        self.items = [] # Blank list
        
    def add_item(self, target_item):
        self.items.append(target_item)
        print(f"Added {target_item.quantity} units of {target_item.name}.")
        
    def display_inventory(self):
        if not self.items:
            print("Inventory is exactly zero.")
        else:
            print(f"\n--- Current Inventory ---")
            for item in self.items:
                print(f"Item: {item.name} | Qty: {item.quantity} | Price: ${item.price}")

def main():
    print("Welcome to the Inventory System!")
    manager = InventoryManager()
    
    # 3. Interactive Loop
    while True:
        print("\n1. Add Item | 2. View Inventory | 3. Quit")
        choice = input("Enter choice: ")
        
        if choice == '1':
            name = input("Enter Item Name: ")
            qty = int(input("Enter Item Quantity: "))
            price = float(input("Enter Item Price: "))
            
            # Create the object and immediately pass to manager
            new_item = Item(name, qty, price)
            manager.add_item(new_item)
            
        elif choice == '2':
            manager.display_inventory()
            
        elif choice == '3':
            print("Shutting down the Inventory Tracker...")
            break # Shatter the infinite loop
            
        else:
            print("Invalid Input. Try again.")

if __name__ == "__main__":
    main()
