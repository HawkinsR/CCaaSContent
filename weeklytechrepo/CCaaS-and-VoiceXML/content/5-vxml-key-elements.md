# Advanced VoiceXML Output

## Learning Objectives

- Identify the purpose of key VXML elements (`<form>`, `<field>`, `<prompt>`, `<grammar>`, `<filled>`, and `<block>`).
- Distinguish between procedural blocks and event-driven form fields.
- Assemble a VXML application combining DTMF input collection, validation, and submission.

## Why This Matters

While an introductory `<vxml>` document simply reads a sentence and hangs up, a functional application must actively engage the caller, collect inputs, validate those inputs against business rules (e.g., ensuring a zip code is exactly 5 digits), and securely transmit that data to a backend server. Mastering these advanced VXML tags allows you to orchestrate complex, stateful dialogues that power automated banking, prescription refills, and package tracking systems.

## The Concept

### The Dialog State Machine

A VXML document behaves as a finite state machine. The primary container for this state machine is the **`<form>`**.
Inside a `<form>`, you place multiple variables (called **form items**). The VXML interpreter's primary job is to "fill" every item in the form. It visits each item sequentially. If an item is already filled, it skips it. If it is empty, it executes the code to collect the missing data.

### Key VXML Elements

#### 1. `<block>` (Procedural Execution)

A `<block>` is a chunk of procedural code. It executes from top to bottom without waiting for user interaction. It's typically used for initial greetings, declaring variables, or performing calculations. Once executed, the interpreter marks the `<block>` as "filled" and moves to the next item.

#### 2. `<field>` (Interactive Data Collection)

A `<field>` is an interactive form item whose sole purpose is to gather input from the caller (DTMF or Voice).

- When the interpreter enters a `<field>`, it immediately plays the nested `<prompt>` (e.g., *"Please enter your 5-digit zip code"*).
- It then *pauses* execution and waits for the user's input.
- If the input matches the defined `<grammar>`, the field's variable is populated with the collected value, marked as "filled", and the interpreter moves on.

#### 3. `<grammar>`

A `<grammar>` defines exactly what inputs the `<field>` is allowed to accept. If a caller says "Apple" when prompted for a Zip Code, the grammar rejects the input. VXML has built-in grammars for boolean responses (`builtin:boolean`), dates (`builtin:date`), and digits (`builtin:digits`).

#### 4. `<filled>` (Validation and Action)

The `<filled>` element acts as an event listener. It triggers the moment an associated `<field>` successfully collects its data.
Inside a `<filled>` block, developers typically write logic to validate the data (e.g., *"Did they enter exactly 5 digits?"*). If the data is valid, they use the `<submit>` or `<goto>` tag to transition the application to the next architectural step.

## Code Example

This example demonstrates a complete data collection flow: Greeting the caller, asking for a zip code, restricting the input, echoing it back for confirmation, and submitting it to a server.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.1" xmlns="http://www.w3.org/2001/vxml">

  <!-- The primary dialog container -->
  <form id="zipcode_collection">

    <!-- 1. Procedural Execution: Run once at the very beginning -->
    <block>
      <prompt>Welcome to the Automated Weather Service.</prompt>
    </block>

    <!-- 2. Interactive Data Collection: Wait for user input -->
    <!-- The name attribute ('user_zip') becomes the variable holding the DTMF integers -->
    <field name="user_zip">
      
      <!-- Ask the user a question -->
      <prompt>
        Using your keypad, please enter your five digit zip code now.
      </prompt>

      <!-- 3. Grammar restriction: The input MUST be exactly 5 digits -->
      <grammar mode="dtmf" 
               type="application/srgs+xml" 
               src="builtin:dtmf/digits?minlength=5;maxlength=5" />

      <!-- Fallback: If they sit in silence (No Input) -->
      <noinput>
        <prompt>I did not hear any input. Please enter your five digit zip code.</prompt>
      </noinput>

      <!-- Fallback: If they enter 6 digits (No Match) -->
      <nomatch>
        <prompt>That was not five digits. Please try again.</prompt>
      </nomatch>

      <!-- 4. The Event Trigger: Executes only AFTER 'user_zip' contains 5 valid digits -->
      <filled>
        <!-- Validate/Confirm back to the user -->
        <prompt>
          I heard you enter the zip code: 
          <!-- The <value> tag dynamically reads the variable's contents -->
          <value expr="user_zip"/>
          Please wait while I retrieve the weather.
        </prompt>

        <!-- Use HTTP GET to submit the 'user_zip' variable to a backend Python/Java server -->
        <!-- The server will process the zip code and return a BRAND NEW VXML document -->
        <submit next="http://api.weather-service.com/get_forecast" namelist="user_zip" method="get"/>
      </filled>

    </field>

  </form>
</vxml>
```

## Summary

- A VXML **`<form>`** attempts to sequentially complete every child "form item" until the entire form is satisfied.
- A **`<block>`** executes procedural logic immediately, while a **`<field>`** pauses execution strictly waiting for user input.
- A **`<grammar>`** guarantees the collected input conforms to specific developer requirements (e.g., minimum digit lengths).
- A **`<filled>`** block acts upon successful data collection, commonly echoing the data back to the caller for confirmation and firing an HTTP **`<submit>`** request to the backend web server for the next computational step.

## Additional Resources

- [W3C: VoiceXML Forms and Fields](https://www.w3.org/TR/voicexml20/#dml2.1)
- [IBM: VoiceXML Form Item execution algorithm](https://www.ibm.com/docs/en/ws-voice-server/6.1.1?topic=guide-voicexml-architecture)
