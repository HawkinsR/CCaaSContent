class Call:
    def __init__(self, caller_name, priority):
        self.caller_name = caller_name
        self.priority = priority

class PriorityACD:
    def __init__(self):
        self.queue = []

    def add_to_queue(self, call):
        print(f"Received call from {call.caller_name} (Priority: {call.priority})")
        # TODO: Add the call to self.queue in the correct position so that
        # the highest priority calls are at the "front" (index 0) of the list.
        # If priorities are equal, the order they were added must be preserved.
        
        # YOUR CODE HERE
        pass

    def tick(self):
        """Simulates an agent becoming available and pulling the next call."""
        if len(self.queue) > 0:
            # Pop the first item off the queue
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
