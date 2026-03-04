# Demo: CCaaS Architecture Flow

## Phase 1: The Concept

**Time:** 10 mins

1. **Context:** Developers are used to HTTP requests (Client -> Server -> Database). Telecommunications relies on different protocols (SIP/RTP) and physical infrastructure. We must bridge the gap between a human placing a phone call and a Web Application handling it.
2. **The Journey of a Call:**
    * **The Origin:** A customer dials an 800 number from their cell phone.
    * **The Carrier:** The PSTN (Public Switched Telephone Network) routes the call to the company's Telecom Carrier (e.g., AT&T, Twilio, Bandwidth).
    * **The Gateway (SBC):** The Session Border Controller is the firewall for Voice. It converts raw telecom audio into SIP (Session Initiation Protocol) packets sent over the internet.
    * **The CCaaS Router:** The "Brain" of the Contact Center (AWS Connect, Genesys, Five9) receives the SIP packets. It checks its database. "Who is calling? What do they want? Which agent is available?"
    * **The Agent Desktop:** The CCaaS platform bridges the audio to a WebRTC (Web Real-Time Communication) softphone sitting inside the agent's web browser, while simultaneously popping up the customer's CRM profile.

## Phase 2: Diagramming (Live Implementation)

**Time:** 10 mins

1. Open `diagrams/architecture.mermaid` (or use a Mermaid Live Editor plugin in your IDE).
2. **Walkthrough:** Step through the sequence diagram, reading the interaction arrows aloud. Ensure trainees understand that the actual Audio (RTP) and the Signaling (SIP) often take different paths, but conceptually, the CCaaS platform is orchestrating everything.
