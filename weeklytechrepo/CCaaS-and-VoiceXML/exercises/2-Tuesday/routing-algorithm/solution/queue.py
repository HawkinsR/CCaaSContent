class Call:
    def __init__(self, caller_name, priority):
        self.caller_name = caller_name
        self.priority = priority

class PriorityACD:
    def __init__(self):
        self.queue = []

    def add_to_queue(self, call):
        print(f"Received call from {call.caller_name} (Priority: {call.priority})")
        
        # Add the call to the list
        self.queue.append(call)
        
        # SOLUTION: Sort the list in place based on the 'priority' attribute.
        # reverse=True ensures the highest numbers are at the front (Index 0).
        # Because Python's built-in Timsort is stable, two calls with Priority 100
        # will stay in the exact order they were originally appended in!
        self.queue.sort(key=lambda x: x.priority, reverse=True)

    def tick(self):
        """Simulates an agent becoming available and pulling the next call."""
        if len(self.queue) > 0:
            next_call = self.queue.pop(0)
            print(f"AGENT ANSWERED: {next_call.caller_name} (Priority {next_call.priority})")
        else:
            print("Queue is empty. Agents are waiting.")

# --- Simulation ---
if __name__ == "__main__":
    acd = PriorityACD()

    # The order these calls arrive in the system:
    acd.add_to_queue(Call("Alice (Standard)", 1))
    acd.add_to_queue(Call("Bob (Standard)", 1))
    acd.add_to_queue(Call("Charlie (Platinum)", 100))
    acd.add_to_queue(Call("Dave (Standard)", 1))
    acd.add_to_queue(Call("Eve (Platinum)", 100))

    print("\n--- Begin Processing Queue ---")
    while len(acd.queue) > 0:
        acd.tick()
