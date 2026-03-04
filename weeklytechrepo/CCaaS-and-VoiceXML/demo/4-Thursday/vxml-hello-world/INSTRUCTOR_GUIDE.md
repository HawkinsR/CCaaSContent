# Demo: VoiceXML Hello World

## Phase 1: The Concept

**Time:** 5 mins

1. **The Origin:** Before AWS Connect had pretty visual builders, how did developers write IVRs in the 1990s and 2000s? VoiceXML (VXML).
2. **The Architecture:** VoiceXML is a standard from the W3C (same people who standardized HTML). Just like a Web Browser reads HTML to render a visual page, a Voice Browser (running on a Telecom Server) reads VXML to "render" an audio phone call.
3. **The Developer Experience:** Instead of programming complex C++ telecom hardware, a developer literally just spins up a Tomcat/Java web server, hosts a static `.vxml` file, and points the Telecom hardware's HTTP request to that URL.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/hello-world.vxml`.
2. **Walkthrough the Syntax:**
    * Show the XML Declaration header (`<?xml version...`).
    * Show the root `<vxml>` tag which scopes the document.
    * Show the `<form>` tag. VXML is built entirely around "Forms". Each distinct interaction (menus, data collection) is a form.
    * Show the `<block>` tag. This executes procedural code sequentially.
    * Show the `<prompt>` tag. This commands the Text-To-Speech (TTS) engine to synthesize the string into audible words.
3. **Visualization:** Explain that if a caller reached the server hosting this file, they would hear a robotic voice say "Hello World. Welcome to Voice XML," and the call would immediately disconnect.
