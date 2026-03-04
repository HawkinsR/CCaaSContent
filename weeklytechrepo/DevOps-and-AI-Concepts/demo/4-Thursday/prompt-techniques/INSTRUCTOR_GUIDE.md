# Demo: Advanced Prompt Engineering Techniques

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Writing "Please write a React button" is called **Zero-Shot Prompting**. You are relying entirely on the AI's pre-trained weights to guess what your internal company style guide looks like. It rarely works perfectly.
2. **Few-Shot Prompting:** The industry standard technique. Instead of just asking for something, you provide the AI with 2-3 explicit *examples* of the input and the exact desired output formatting before giving it the real task.
3. **The Impact:** Few-shot prompting drastically reduces hallucinations and forces the AI to conform to strict JSON or structural requirements, which is vital if the AI is part of an automated pipeline.

## Phase 2: Live Prompting

**Time:** 10 mins

1. Open your LLM.
2. **Scenario: The Bad Zero-Shot**
    * *Prompt:* "Extract the name and age from this sentence: 'John is twenty two years old.' Return it as JSON."
    * *Result:* The AI usually returns conversational fluff ("Here is your JSON:") wrapped around the payload, breaking any automated parsers.
3. **Scenario: The Good Few-Shot**
    * *Prompt:*

    ```
    Extract the name and age from the text and return purely valid JSON. No conversational text.
    
    Text: "Mary is 34."
    Output: {"name": "Mary", "age": 34}
    
    Text: "The suspect, Robert, turns forty tomorrow."
    Output: {"name": "Robert", "age": 40}
    
    Text: "John is twenty two years old."
    Output: 
    ```

    * *Result:* The AI will almost always return exactly `{"name": "John", "age": 22}` with no markdown formatting or conversation, proving the power of context examples.
