# Demo: The Agile Board and the Pipeline

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Developers don't just randomly write code. Work is tracked in a ticketing system (Jira, Trello, GitHub Projects).
2. **The Board:** Explain the Kanban flow. "To-Do" -> "In Progress" -> "In Review" -> "Done".
3. **The DevOps Tie-In:** Modern Agile boards aren't just sticky notes on a wall; they are wired into the GitHub Actions we built yesterday. When a PR is opened, the ticket magically moves to "In Review." When the PR is merged, the pipeline runs, deploys the code, and the ticket magically moves to "Done."

## Phase 2: The Visual Walkthrough

**Time:** 10 mins

1. *Instructor Note: The best way to demo this is to create a free GitHub Project board attached to a repo. If you cannot do that live, use `images/agile-flow.png` (provided in slides) or draw it.*
2. **The Simulation:**
    * Create a ticket: "Add Login Button."
    * Assign it to yourself. Move it to "In Progress".
    * Explain the daily standup updates.
    * Simulate opening a Pull Request titled `Fixes #123`.
    * Show how GitHub's automation closes the ticket and moves the card when the PR is merged.
