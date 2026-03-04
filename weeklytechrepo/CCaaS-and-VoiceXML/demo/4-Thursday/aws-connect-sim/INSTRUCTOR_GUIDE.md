# Demo: Amazon Connect Simulation

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The flowcharts we built yesterday are conceptually sound, but how are they actually built in the real world today? Modern CCaaS platforms like AWS Connect or Genesys Cloud use visual "low-code/no-code" flow builders.
2. **Contact Flows:** AWS Connect calls IVR trees "Contact Flows". You literally drag-and-drop the blocks we diagrammed yesterday onto a canvas over the web browser.
3. **Behind the Scenes:** It is highly important to emphasize to software engineers what these visual builders are doing: When you click "Publish" on an AWS Contact Flow, the cloud provider is quietly generating thousands of lines of raw Voice eXtensible Markup Language (VoiceXML) behind the scenes to execute the call logic.

## Phase 2: The Visual Walkthrough

**Time:** 10 mins

1. *Since we cannot provision real AWS Connect instances for 30 students instantly, walk through screenshots of the interface found in AWS Documentation or present a slide deck.*
2. **Key Blocks to Highlight:**
    * **Play Prompt:** Replaces our Mermaid `["Say"]` blocks.
    * **Get Customer Input:** Replaces our Mermaid `{Input}` diamonds. Show how they support both "DTMF" (keypad) and "Voice" (Amazon Lex NLP integration).
    * **Transfer to Queue:** Show how this block passes control from the IVR to the ACD routing engine.
    * **Invoke AWS Lambda:** This is the most critical block for developers. **Data Dips**. If the user types in their account number, the IVR flow pauses, triggers an invisible Python/Node Serverless Lambda function to check a DynamoDB database, and the Lambda returns success/fail back to the IVR flow to determine the next block.
