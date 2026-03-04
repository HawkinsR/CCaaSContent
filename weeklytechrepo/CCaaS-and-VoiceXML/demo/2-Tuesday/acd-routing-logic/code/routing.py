class Agent:
    def __init__(self, name, skills):
        self.name = name
        self.skills = set(skills)
        self.is_available = True

class Call:
    def __init__(self, caller_id, required_skills):
        self.caller_id = caller_id
        self.required_skills = set(required_skills)

class SimpleACD:
    def __init__(self, agents):
        self.agents = agents

    def route_call(self, call):
        print(f"\nACD: Incoming call from {call.caller_id} requires: {call.required_skills}")
        
        for agent in self.agents:
            if not agent.is_available:
                continue
            
            # SKILLS-BASED ROUTING LOGIC:
            # Does the agent possess all the skills required by the call?
            if call.required_skills.issubset(agent.skills):
                print(f"ACD: MATCH FOUND! Routing call to {agent.name}")
                agent.is_available = False # Agent is now busy on the call
                return
                
        print(f"ACD: No available agents match {call.required_skills}. Placing caller in queue...")

# --- Simulation ---
if __name__ == "__main__":
    # 1. Setup our Agents
    agents_pool = [
        Agent("Alice", ["english", "sales"]),
        Agent("Bob", ["english", "spanish", "sales"]),
        Agent("Charlie", ["english", "spanish", "billing"])
    ]
    acd = SimpleACD(agents_pool)

    # 2. Simulate Calls
    call_1 = Call("555-0001", ["english", "sales"])
    acd.route_call(call_1) # Should route to Alice

    call_2 = Call("555-0002", ["spanish", "billing"])
    acd.route_call(call_2) # Should route to Charlie

    call_3 = Call("555-0003", ["spanish", "sales"])
    acd.route_call(call_3) # Should route to Bob

    call_4 = Call("555-0004", ["french", "sales"])
    acd.route_call(call_4) # Should fail queue because no agents speak French!
