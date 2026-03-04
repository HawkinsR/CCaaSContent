# Lab: AI Pair Programming (Debugging)

## The Scenario

Your company has a legacy `UserAuthenticator` class written by a developer who has since left the company. It is known to be extremely buggy and occasionally crashes the production server, but nobody has had time to read the messy code to figure out why.

Your task is to fix the class, but you are not allowed to manually rewrite it. You must pair program with an AI (ChatGPT, Gemini, Claude) to do the heavy lifting.

## Deliverables

1. Navigate to the `starter_code` directory and open `authenticator.py`.
2. Do not fix the code yourself. Instead, open your AI tools.
3. Your goal is to write a single, comprehensive Prompt that:
    * Provides the AI with the code.
    * Tells the AI the context (it's a buggy authentication class).
    * Asks the AI to identify all logic flaws and potential runtime exceptions.
    * Asks the AI to return the fully corrected class.
4. **Documentation:** Create a file called `Pair_Programming_Log.md`. In this file, paste the exact Prompt you used, list the bugs the AI successfully identified, and indicate whether the final code it generated worked on the first try, or if you had to iterate with follow-up prompts.

## Hint

There are at least 3 distinct bugs in `authenticator.py`, ranging from syntax logic (using `==` instead of `is`) to catastrophic runtime errors (modifying a dictionary while iterating over it, or unprotected division by zero if tracking metrics).

## Definition of Done

- A `Pair_Programming_Log.md` document exists detailing the interaction.
* The `authenticator.py` file has been replaced with the AI's corrected, bug-free version.
