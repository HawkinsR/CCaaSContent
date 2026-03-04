# Lab: The Prompt Battle

## The Scenario

This is a timed, competitive pair programming exercise. Your pair is competing against the rest of the class.

**The Goal:** Write a single, comprehensive Prompt that forces an AI (ChatGPT/Claude/Gemini) to generate a complete, working HTML/CSS/JS file containing an interactive "Login Modal" component.

## The Rules

1. **One-Shot Only:** You are not allowed to chat with the AI. You have exactly one try. You craft your perfect prompt in the `starter_code/prompt.txt` file. When time is up, you copy/paste it into an AI. Whatever code it outputs on the first try is your final submission.
2. **Specific Requirements:** The Login Modal must meet these strict criteria to be considered "working":
    * It must have an "Email" input field.
    * It must have a "Password" input field.
    * It must have a "Submit" button.
    * The Submit button must be disabled (unclickable) until *both* fields have at least 1 character typed into them.
    * The CSS styling must look modern (rounded corners, drop shadow).
    * It must be contained within a single `index.html` file (using `<style>` and `<script>` tags) so it can be double-clicked and previewed instantly in a browser.

## The Scoring

The instructor will run the prompt you generated and save the AI's output as an `index.html`.
Your pair wins points based on:

1. Does it run without syntax errors?
2. Does the Submit button strictly follow the validation logic?
3. Did the AI actually follow all instructions in *one shot*?

## Deliverables

1. Navigate to the `starter_code` directory.
2. Open `prompt.txt`.
3. Spend 15 minutes drafting the perfect prompt.
    * *Hint: Use the techniques discussed in lecture. Assign a Persona. Use Few-Shot examples if necessary. Be extremely explicit about the Javascript logic.*
