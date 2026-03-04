# Demo: AI Doc Generation & Visualization

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Developers hate writing documentation. It is often outdated the moment it is written.
2. **The AI Advantage:** Generative AI is uniquely suited for translating code into human-readable text (and vice versa).
3. **Visualization:** We will take a raw SQL database schema and ask the AI to not just document it, but specifically write Mermaid.js code to generate a visual Entity-Relationship (ER) diagram.

## Phase 2: Live Prompting

**Time:** 10 mins

1. Open your LLM. Show the class `code/schema.sql`.
2. **The Prompt:**
    *"I have a PostgreSQL database schema. First, write a brief, professional Markdown README explaining the purpose of this database to a new junior developer. Second, generate the exact Mermaid.js `erDiagram` syntax that visually maps these tables, their primary keys, and their relationships (one-to-many, etc.)."*
    *(Paste the contents of `schema.sql`)*
3. **The Review:**
    * Evaluate the Markdown documentation it produced.
    * Take the Mermaid.js output, open `https://mermaid.live/`, and paste it in. Show the class how the AI instantly turned 30 lines of raw SQL into a beautiful, visual architectural diagram.
