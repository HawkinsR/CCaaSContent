# Demo: Automatic Contact Distributor (ACD) Routing Logic

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** In the 1980s, call centers used "Hunt Groups." If you dial Sales, it rings Phone A. If Phone A is busy, it rings Phone B. This was wildly inefficient.
2. **The ACD:** The Automatic Contact Distributor is the software engine that holds callers in a queue (listening to hold music) and continuously scans a database of Agents to find the perfect match.
3. **Skills-Based Routing:** Modern ACDs don't just find the *longest idle* agent; they find the *most qualified* agent. If a Spanish-speaking customer calls about a billing error, the ACD must find an agent tagged with `[Language: Spanish, Skill: Billing]`.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/routing.py`. We are using Python for its pseudo-code readability.
2. **Walkthrough:**
    * Show the `Agent` and `Call` classes. Note the `skills` and `required_skills` lists.
    * Walk through the `SimpleACD.route_call()` method.
    * Point out the `set.issubset()` logic. This is the core of Skills-Based routing. The Agent's skills must fully encompass the Call's required skills.
3. **Execute the Script:**
    * Run `python routing.py`.
    * Read the output to the class. Show how the Spanish Billing call bypasses Alice (missing Spanish) and Bob (missing Billing), and perfectly lands on Charlie.
