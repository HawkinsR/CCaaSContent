# Demo: VoiceXML Data Collection and Submission

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Pushing 1, 2, or 3 for a menu is easy. But how do we collect complex data, like a Zip Code, Credit Card Number, or Phone Number?
2. **The `<field>` tag:** This is the core data collection tool in VXML. It essentially pauses the script and waits for the user to type on their keypad (or speak).
3. **The Built-in Grammars:** Telecom servers have built-in regex engines. By setting the `type="digits?length=5"`, the server automatically knows to reject inputs that are 4 digits or 6 digits, and will re-prompt the user without us writing any code!
4. **Submission:** Once the data is collected, we need to send it to our Spring Boot backend. VXML uses the `<submit>` tag, which is identical in concept to an HTML `<form action="/submit" method="POST">`.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/zip_code.vxml`.
2. **Walkthrough the Code:**
    * Show the `<form>` and the `<field name="zipCode">`. Emphasize the `type="digits?length=5"` attribute.
    * Walk through the nested `<prompt>`. This tells the user what to type.
    * Show the `<filled>` tag. Explain that this block of code *only* executes if the user successfully satisfies the grammar constraint (typing exactly 5 digits). It will not execute if they hang up or fail.
    * Inside `<filled>`, show the `<submit>` tag. We are explicitly sending the `zipCode` variable to a fictitious URL (`https://api.mycompany.com/verifyZip`) via an HTTP GET request. The VXML engine handles the HTTP request entirely on its own!
