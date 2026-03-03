# Demo: Building a Complete JMS Client

## Overview

**Type:** Code-focused
**Time:** 30-40 minutes
**Goal:** Build a complete JMS client application that sends and receives structured messages using multiple message types.

---

## Phase 1: The Concept (Brief)

**Time:** 5 minutes

- "Yesterday we sent simple text messages. Today let's build a more realistic client that uses different message types and message post-processors."
- Reference the written content on JMS Client Patterns.

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-35 minutes

### Step 1: Create a Document Model

Open `code/Document.java` -- a `Serializable` POJO representing a document to process.

### Step 2: Create the Producer with Multiple Message Types

Open `code/DocumentProducer.java`:

- Show `sendText()` sending a simple string.
- Show `sendObject()` sending a serializable Java object.
- Show `sendWithHeaders()` using a `MessagePostProcessor` to set custom headers (correlationId, contentType).
- "The post-processor is a callback -- Spring calls it right before sending, giving you a chance to modify the message."

### Step 3: Create a Consumer with a Message Selector

Open `code/DocumentConsumer.java`:

- Show a basic listener on `"document-queue"`.
- Show a filtered listener using `selector = "priority = 'HIGH'"`.
- "Message selectors let you filter at the broker level -- the consumer only receives messages that match."

### Step 4: Create a Controller

Open `code/DocumentController.java` with endpoints to send text, object, and high-priority messages.

### Step 5: Run and Test

1. `POST /api/documents/text` -- send a plain text message.
2. `POST /api/documents/object` -- send a serialized Document object.
3. `POST /api/documents/priority` -- send a high-priority message with selector headers.
4. Watch the console: the filtered listener only picks up high-priority messages.

---

## Key Talking Points

- Real applications use multiple message types and custom headers for routing.
- Message selectors filter at the broker level, reducing unnecessary processing.
- `MessagePostProcessor` adds headers without changing the core send logic.
- "These are the patterns you will see in production JMS systems."
