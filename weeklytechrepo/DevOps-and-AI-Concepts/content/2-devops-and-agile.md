# DevOps and Agile Methodologies

## Learning Objectives

- Compare traditional Waterfall methodologies with Agile development.
- Describe how DevOps pipelines physically enable Agile Sprints.
- Identify the roles within a Scrum team (Product Owner, Scrum Master, Developers).

## Why This Matters

If CI/CD is the engine of software delivery, Agile is the steering wheel. Writing code is useless if you are building a product the customer doesn't actually want. Historically, companies spent 18 months building software in secret, only to release it and discover the market had moved on. Agile methodologies guarantee that development teams are constantly communicating with the business, demonstrating working software every two weeks, and adapting to change. However, you cannot release software every two weeks without a DevOps pipeline. The two concepts are permanently intertwined.

## The Concept

### The Waterfall Methodology (The Legacy Approach)

Before modern web development, software was built like a skyscraper—sequentially.

1. **Requirements:** The business spends 6 months writing a 500-page document detailing exactly what the software must do.
2. **Design:** Architects spend 3 months designing the database schema.
3. **Implementation:** Developers spend 12 months writing the code.
4. **Testing:** QA spends 3 months testing the code.
5. **Deployment:** The code is finally given to the customer.

**The Fatal Flaw:** If the customer changes their mind in Month 14, the entire project fails. You cannot reverse a waterfall.

### The Agile Philosophy

Agile was born in 2001 to destroy the Waterfall model entirely. The core philosophy is **Iterative Development**.
Instead of building a massive software suite over two years, Agile teams build the absolute minimum viable product (MVP) in two weeks, release it to the customer, get their feedback, and spend the next two weeks building the *next* most important feature.

### Scrum Framework

Scrum is the most popular implementation of Agile. It relies on fixed-length, recurring blocks of work called **Sprints** (usually 2 weeks long).

#### The Roles

- **Product Owner (PO):** Represents the customer and the business. They maintain the **Product Backlog** (a prioritized list of every feature the app needs) and write the "User Stories" (e.g., *"As a user, I want to reset my password so I can regain access"*).
- **Scrum Master:** A facilitator who ensures the team adheres to Agile principles and brutally removes any blockers preventing the developers from writing code.
- **The Development Team:** The cross-functional engineers (frontend, backend, QA) who actually build the software.

#### The Ceremonies

- **Sprint Planning:** The team looks at the Product Owner's backlog and commits to exactly how many tickets they can finish in the upcoming 2 weeks.
- **Daily Standup:** A strict 15-minute daily meeting where everyone answers three questions: What did I do yesterday? What am I doing today? Is anything blocking me?
- **Sprint Review:** At the end of the 2 weeks, the team demonstrates the *working, deployed software* to the stakeholders.
- **Sprint Retrospective:** The team privately discusses what went wrong during the sprint and how to improve their processes for the next one.

### How DevOps Enables Agile

Agile mandates that a team must produce "shippable software" at the end of every 2-week Sprint.
If your company still relies on the Operations team to manually configure servers and FTP files (which takes 3 weeks), you physically cannot execute a 2-week Sprint.
**DevOps is the technological prerequisite for Agile.**
The automated CI/CD pipeline ensures that when the 2-week Sprint finishes, the code is instantly tested, packaged, and deployed to Staging for the Sprint Review demonstration.

## Summary

- **Waterfall** requires massive upfront planning and assumes requirements never change, often resulting in failed multi-year projects.
- **Agile** embraces change by building software in small, iterative two-week cycles, constantly gathering customer feedback.
- **Scrum** is the standard Agile framework, utilizing structured Sprints, Product Owners, and Daily Standups to maintain velocity.
- **DevOps** (specifically automated CI/CD) is the mandatory engine that makes rapid Agile two-week release cycles technically possible.

## Additional Resources

- [Atlassian: Agile Project Management](https://www.atlassian.com/agile)
- [Scrum.org: What is Scrum?](https://www.scrum.org/resources/what-is-scrum)
