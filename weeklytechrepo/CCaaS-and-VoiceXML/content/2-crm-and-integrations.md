# CRM Systems and CCaaS Integrations

## Learning Objectives

- Define the purpose of a Customer Relationship Management (CRM) system.
- Understand the concept of "Data-Dips" in telephony routing.
- Describe the mechanism and value of "Screen Pops" for contact center agents.

## Why This Matters

A CCaaS routing engine (the system that answers the phone and decides where to send the call) is incredibly intelligent at managing queues, but it knows absolutely nothing about the *person* calling. It only sees a 10-digit phone number. To provide personalized, efficient customer service, the routing engine must instantly connect to the company's "brain"—the CRM. Without CRM integrations, customers must repeat their account numbers, their names, and their problems to every new agent they speak with, which is the number one cause of customer dissatisfaction globally.

## The Concept

### Customer Relationship Management (CRM)

A CRM is the central database where a company stores every interaction, purchase, and detail about a customer. Salesforce is the most prominent global example, alongside Zendesk, HubSpot, and Microsoft Dynamics.

- When a customer buys a product online, the transaction is logged in the CRM.
- When a customer emails a complaint, a "Support Ticket" or "Case" is created in the CRM.
- If the CRM goes down, the company is effectively blind; agents do not know who is calling or what they purchased.

### The Problem: Siloed Systems

Historically, the telephony system (PBX) and the CRM sat on two different servers and rarely spoke to each other.
When a call arrived, the agent answered the phone, asked the customer for their Account Number, manually typed that number into the CRM search bar, hit Enter, waited for the page to load, and then began assisting the customer. This manual process takes 15-30 seconds per call. If a center handles 100,000 calls a day, those 30 seconds equate to millions of dollars in wasted hourly wages annually.

### Integration: Data-Dips

To solve this, modern CCaaS platforms integrate directly with CRMs via REST APIs.

When a customer calls in, before the phone even rings on an agent's desk, the CCaaS routing engine performs a **Data-Dip**.

1. The CCaaS platform reads the caller's phone number (Caller ID / ANI).
2. It sends lightning-fast HTTP GET request to the Salesforce REST API: `GET /customers?phone=5551234567`.
3. Salesforce responds with a JSON payload: `{"name": "Alice Smith", "vip_status": true, "open_case": "Case# 9982"}`.
4. The CCaaS routing engine now knows exactly who is calling before they even say "Hello." It can use this JSON payload to route VIP callers to specialized priority queues.

### Integration: Screen Pops

Once the CCaaS engine decides which agent should receive the call, it must pass that context to the human.
A **Screen Pop** automates the manual search process.

When the CCaaS platform sends the ringing call to Agent Bob's web browser, it simultaneously sends a command to the embedded Salesforce window. By the time Agent Bob clicks "Accept Call," Salesforce has already automatically loaded Alice Smith's customer profile and her open Support Ticket right on his screen.

Bob's opening line transforms from, *"Hello, who am I speaking with today?"* to, *"Hello Alice, I see you are calling about your open ticket regarding a delayed shipment; how can I help?"*

## Summary

- A **CRM** (e.g., Salesforce, Zendesk) stores the comprehensive history of a customer's interactions and purchases.
- CCaaS systems perform **Data-Dips** via REST APIs to query the CRM using the Caller ID, fetching contextual JSON payloads before routing the call.
- **Screen Pops** automatically load the relevant CRM customer profile or Support Ticket on the agent's screen the exact millisecond the call connects, eliminating manual search time and significantly improving the customer experience.

## Additional Resources

- [Salesforce: What is CRM?](https://www.salesforce.com/crm/what-is-crm/)
- [Zendesk: Guide to CTI (Computer Telephony Integration)](https://www.zendesk.com/blog/what-is-cti/)
