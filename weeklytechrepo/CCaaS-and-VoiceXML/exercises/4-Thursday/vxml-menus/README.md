# Lab: Collaborative VoiceXML Menus

## The Scenario

Your Pair Programming team has been asked to write the VoiceXML code for the "Main Menu" block of our airline IVR flowchart from yesterday.

When a caller hits this file, they should hear a prompt offering them three choices. Based on the number they press on their touch-tone keypad (DTMF), the VXML script should play a different confirmation message and then disconnect.

## Business Requirements

* **The Menu:** The IVR must read the Prompt: "For Reservations, press 1. For Customer Service, press 2. For Baggage Claim, press 3."
* **The Choices:**
  * If they press `1`: Play "Transferring to Reservations."
  * If they press `2`: Play "Transferring to Customer Service."
  * If they press `3`: Play "Transferring to Baggage Claim."
* **Disconnection:** After the specific choice prompt is played, the call must hang up (exit).

## Deliverables (Pair Programming)

1. Navigate to the `starter_code` directory and open `menu.vxml`.
2. Notice we are using a `<menu>` tag instead of a `<form>`. The menu tag is a specialized VXML element perfectly designed for "press 1 for X, 2 for Y".
3. Add a `<prompt>` block inside the menu to read out the instructions.
4. Add three `<choice>` tags.
    * The `dtmf` attribute tells the telecom server which key to listen for.
    * The `next` attribute tells the telecom server which internal `#id` to jump to if that key is pressed.
5. Below the menu, create three separate `<form>` blocks mapping to the IDs you defined in step 4.
6. Inside each form, use `<block>` and `<prompt>` to play the confirmation message, and `<exit/>` to end the call.

## Definition of Done

- The VXML document contains a valid `<menu>` with a localized `<prompt>`.
* Three `<choice>` tags map DTMF inputs `1`, `2`, and `3` to three distinct UI state forms.
* Three `<form>` tags exist at the bottom of the document to handle the routing and play the final text-to-speech recordings.
