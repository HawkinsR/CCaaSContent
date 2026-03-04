# Lab: VoiceXML Capstone (Pizza Delivery)

## The Scenario

A local Pizza chain is losing money because they can't afford to hire enough phone agents to take orders during Friday night rushes. They want to automate the basic order-taking process using an IVR system built in purely statically hosted VoiceXML.

You have been tasked with building the interaction script.

## Deliverables

1. Navigate to the `starter_code` directory and open `pizza.vxml`.
2. Review the basic layout. We are using a `<form>` block to encompass the entire interaction.
3. **Data Collection 1 (Phone Number):**
    * Create a `<field>` named `phoneNumber` with a `type="digits?length=10"`.
    * Add a prompt asking the customer for their 10-digit phone number.
4. **Data Collection 2 (Pizza Size):**
    * Create a `<field>` named `pizzaSize`.
    * Since "Small", "Medium", and "Large" are not built-in telecom grammars, we provided an inline `<grammar>` block. This allows the telecom server's speech recognition engine to listen for exactly those three words.
    * Add a prompt asking the customer what size pizza they want.
5. **Confirmation and Submission:**
    * Create a `<filled>` block. *Crucially, in VXML, a `<filled>` block placed OUTSIDE of a generic `<field>` tag will only execute when ALL fields in the form have successfully collected data.*
    * Inside the filled block, add a prompt that repeats the order back (e.g., "Thank you. We will send a [pizzaSize] pizza right away to the location tied to [phoneNumber]."). Use the `<value expr=""/>` syntax.
    * Add a `<submit>` tag simulating sending these two variables via a `POST` request to `https://api.pizzachain.com/orders`.

## Definition of Done

- The single VXML file contains two distinct data collection fields.
* The first field leverages the built-in numeric digit constraint.
* The second field leverages the provided custom SRGS XML speech grammar.
* The `<filled>` block accurately echoes the variables back for confirmation before submitting.
