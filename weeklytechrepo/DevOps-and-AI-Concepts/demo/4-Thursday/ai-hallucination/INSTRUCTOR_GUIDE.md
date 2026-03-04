# Demo: Forcing an AI Hallucination

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The biggest risk of GenAI in software engineering is "Hallucination." LLMs do not *know* facts. They are probabilistic math engines predicting the next most likely token.
2. **The Danger:** If you ask an AI how to use a library that doesn't exist, it won't say "I don't know." It will often invent a perfectly plausible-looking library, complete with fake methods, fake parameters, and fake documentation.
3. **The Lesson:** Developers must fundamentally mistrust AI output. You must verify every generated method against official documentation.

## Phase 2: Live Prompting

**Time:** 10 mins

1. Open your LLM.
2. **The Scenario (Copy/Paste this prompt):**
    * *Prompt:* "I am writing a Python script and I need to parse a proprietary `.glorp` file format. Please write the Python code to import the `glorp-parser-x9` library and extract the metadata headers."
3. **The Review:**
    * `.glorp` is a completely made up file extension. `glorp-parser-x9` does not exist.
    * Read the AI's response to the class.
    * **Crucial Point:** Look at how confidently the AI writes `import glorp_parser`. It might even invent classes like `GlorpDocument()` and methods like `.get_headers()`. If a Junior Dev pasted this into their IDE, they would spend hours wondering why `pip install glorp-parser-x9` is failing.
