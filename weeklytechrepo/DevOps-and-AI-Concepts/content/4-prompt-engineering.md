# Prompt Engineering

## Learning Objectives

- Define Prompt Engineering and its necessity in modern software development.
- Differentiate between Zero-Shot and Few-Shot prompting techniques.
- Apply advanced techniques such as Chain-of-Thought prompting and Persona assignment.

## Why This Matters

An AI model is only as intelligent as the instructions it is given. If you ask an LLM a vague, poorly constructed question, you will receive a vague, unhelpful, and potentially hallucinated answer. **Prompt Engineering** is the formal discipline of designing, refining, and optimizing text inputs to force Generative AI models to returning highly accurate, formatted, and predictable outputs. As LLMs become integrated into enterprise APIs, the ability to craft prompts that guarantee JSON consistency or strict adherence to business rules becomes a dedicated engineering skillset.

## The Concept

### What is a Prompt?

A **Prompt** is the explicit natural language text a human provides to an LLM (e.g., ChatGPT, Gemini, Claud) to instruct it to perform a task.

- **Bad Prompt:** "Write me a Python script to check websites." *(Too vague: What websites? How often? How do I know if it succeeded?)*
- **Good Prompt:** "Write a Python script using the `requests` library that pings `google.com` every 5 seconds. If the status code is not 200, log the exact timestamp and error code to a file named `outage.log`."

### Prompting Techniques

#### 1. Zero-Shot Prompting

You ask the AI to perform a task without providing any examples of the desired output format. You assume the model has enough inherent knowledge from its training data to understand the request.

- **Prompt:** "Classify the sentiment of this text as Positive, Neutral, or Negative: 'I absolutely hated the new interface update.'"
- **AI:** "Negative."

#### 2. Few-Shot Prompting

You provide the AI with a small number of examples (shots) within the prompt itself to demonstrate exactly the pattern, tone, or structure you demand for the final answer. This drastically reduces hallucinations and formatting errors.

- **Prompt:**

    ```text
    Extract the company name and location from the sentence. Format it exactly like a JSON object.

    Sentence: "Apple is headquartered in Cupertino, California."
    Output: {"company": "Apple", "location": "Cupertino, California"}
    
    Sentence: "Spotify was founded in Stockholm, Sweden."
    Output: {"company": "Spotify", "location": "Stockholm, Sweden"}
    
    Sentence: "The automotive giant Ford operates out of Dearborn, Michigan."
    Output:
    ```

- **AI:** `{"company": "Ford", "location": "Dearborn, Michigan"}`

#### 3. Chain-of-Thought (CoT)

Used for complex reasoning or mathematical problems. If you ask a complex riddle instantly, the AI often guesses incorrectly because it rushes to the final token.
**Chain-of-Thought** explicitly forces the AI to "show its work" step-by-step before outputting the final answer. This dramatically increases accuracy.

- **Prompt:** "If John has 5 apples, gives 2 to Mary, buys 10 more, and splits them evenly with Dave, how many does John have? **Think through this step-by-step.**"
- **AI:**
    1. John starts with 5.
    2. Gives 2 to Mary. (5 - 2 = 3).
    3. Buys 10 more. (3 + 10 = 13).
    4. Splits them evenly with Dave. (13 / 2 = 6.5).
    5. **Final Answer:** John has 6.5 apples.

#### 4. System Prompts and Personas

You explicitly define who the AI "is" before asking it a question. This limits the scope of its knowledge and sets a consistent tone.

- **Prompt:** "Act as a Senior Database Administrator with 20 years of experience tuning PostgreSQL databases. Do not explain basic concepts. Review the following SQL query and optimize it for a table containing 1 billion rows."
- **Result:** The AI assumes the persona, skipping the condescending "SQL is a language used for databases..." introduction and immediately outputs advanced query execution plans.

## Summary

- **Prompt Engineering** is the methodical refinement of text inputs to extract accurate, deterministic outputs from probabilistic LLMs.
- **Zero-Shot** relies on the model's base training; **Few-Shot** provides concrete examples to guarantee formatting.
- **Chain-of-Thought (CoT)** forces step-by-step reasoning for complex logic.
- **Personas (System Prompts)** strictly define the AI's identity, tone, and assumed knowledge level for highly specific tasks.

## Additional Resources

- [OpenAI: Prompt Engineering Best Practices](https://platform.openai.com/docs/guides/prompt-engineering)
- [PromptingGuide.ai: A Comprehensive Guide](https://www.promptingguide.ai/)
