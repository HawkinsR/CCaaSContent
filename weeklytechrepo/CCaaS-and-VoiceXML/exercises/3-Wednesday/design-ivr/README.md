# Lab: Design your first IVR Flow

## The Scenario

Global Airlines Inc. is launching a new customer support hotline. Instead of writing code. You are playing the role of the Voice UI Designer.

You need to create the logical flowchart for the new IVR using Mermaid.js syntax.

## Business Requirements

* **Greeting:** The IVR must welcome the caller.
* **Main Menu:** Must offer three options:
    1. Check Flight Status (requires entering a 3-digit flight number).
    2. Book a New Flight.
    3. Speak to a Representative.
* **Data Collection Element:** If they choose "Flight Status", they must be prompted to enter a 3-digit number.
* **Error Handling (The Retry Loop):** If they enter less than 3 digits, or a letter, the system played an error message and loops *back* to ask for the number again.
* **Success state:** "Your flight is on time." -> Hang up.
* **Transfer states:** Both the "Book a New Flight" and "Speak to a Representative" options must terminate in a Transfer block (handing the call off to a human).

## Deliverables

1. Navigate to the `diagrams` directory and open `airline_ivr.mmd`.
2. Write the standard Mermaid `graph TD` structure.
3. Map out the flow defined in the business requirements above.
4. Ensure you use diamonds `{}` for decision points/inputs, and brackets `[]` for prompt playbacks.

## Definition of Done

- Rendering the `.mmd` file in a Markdown previewer or online editor produces a top-down flowchart that logically satisfies all business constraints, specifically the retry-loop for bad input.
