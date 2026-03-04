# Demo: Mapping an IVR Call Flow

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The Interactive Voice Response (IVR) is the automated "robot" you speak to before reaching a human. Designing an IVR is a unique software engineering challenge because there is no visual UI (User Interface); there is only a VUI (Voice User Interface).
2. **State Machines:** Explain that an IVR is fundamentally a giant State Machine. The caller is in exactly one state (e.g., "Main Menu") and can only transition to defined next states based on inputs.
3. **Visualization:** Because IVRs are inherently complex logic trees with loops and dead-ends, Product Managers and Engineers *always* use Flowcharts to design them before writing a single line of code.

## Phase 2: Diagramming (Live Implementation)

**Time:** 10 mins

1. Open `diagrams/banking_ivr.mermaid`. Use a Mermaid viewer or extension.
2. **Walkthrough the Tree:**
    * Start at the `WelcomePrompt`. Mention that this is usually a recording play block.
    * Show the main branching logic (`GetInput`). This is where DTMF (key presses) or Speech Recognition occurs.
    * Follow the "Checking Account" path. Notice the authentication loop. If the user fails 3 times, we don't just hang up; we gracefully route them to an Agent.
    * Explain the `Transfer` blocks. This is where the IVR finishes its job and hands the SIP packet over to the ACD Routing Engine we built yesterday.
