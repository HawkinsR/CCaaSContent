# Demo: Introduction to AI Prompting

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Large Language Models (LLMs) like ChatGPT, Gemini, and Claude are neural networks trained on massive amounts of data, including GitHub. They "understand" code incredibly well.
2. **Baseline Prompting:** But they only give you what you ask for. Vague prompts get vague answers. "Explain this code" is bad. "Explain this Python List Comprehension and suggest a more readable, albeit verbose, alternative" is good.
3. **The Mindset Shift:** Junior developers ask AI to *write* the code for them. Senior developers ask AI to *explain, critique, and document* the code they are writing.

## Phase 2: Live Prompting (The Oracle)

**Time:** 10 mins

1. Open your preferred LLM (ChatGPT, Gemini, etc.) on screen.
2. **Scenario A: The Unreadable Regex.**
    * Paste this string: `^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$`
    * Prompt: *"I am a junior Java developer. Explain exactly what this Regular Expression does, piece by piece. Then provide three examples of strings that it would match, and three that it would reject."*
    * Review the output with the class. Show how the AI correctly identifies it as a rudimentary Email validator.
3. **Scenario B: The Bad SQL.**
    * Paste this string: `SELECT * FROM users u LEFT JOIN orders o ON u.id = o.user_id WHERE u.age > 18;`
    * Prompt: *"I am doing a code review. Is there anything theoretically wrong or inefficient with this SQL query? Assume the Tables are massive."*
    * Review the output. The AI should point out that `SELECT *` is bad practice, and ask if columns are indexed.
