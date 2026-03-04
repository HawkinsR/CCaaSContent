# Continuous Integration and Continuous Delivery (CI/CD)

## Learning Objectives

- Differentiate between Continuous Integration (CI), Continuous Delivery (CD), and Continuous Deployment (CD).
- Explain the purpose of an automated pipeline in the Software Development Life Cycle (SDLC).
- Identify common CI/CD tools used in the industry.

## Why This Matters

If DevOps is the philosophy, CI/CD is the engine that actually powers it. Asking a human developer to manually run unit tests, compile a Java `.jar` file, SSH into a remote Linux server, and upload the file via FTP is incredibly dangerous. Humans forget steps, use the wrong compiler versions, and accidentally delete production files. Mastering CI/CD means you understand how to automate the entire pathway from a developer's laptop to the customer's web browser, ensuring every release is predictable, secure, and fast.

## The Concept

### The Pipeline

A CI/CD pipeline is a series of automated steps that execute sequentially every time a developer commits code to a central repository (like GitHub or GitLab). If any step in the pipeline fails, the entire process halts, and the code is prevented from reaching production.

### Continuous Integration (CI)

**Continuous Integration** is the practice of merging all developers' working copies into a shared mainline (the `main` branch) several times a day.

When a developer opens a Pull Request to merge their feature branch into `main`, the CI server (e.g., GitHub Actions, Jenkins) automatically triggers on the cloud.
The CI phase typically includes:

1. **Linting:** Does the code meet formatting standards (e.g., pylint)?
2. **Building:** Does the code compile successfully without syntax errors?
3. **Testing:** Do the automated Unit Tests and Integration Tests pass?
4. **Security Scanning:** Are there any known vulnerabilities or accidentally committed API keys?

If all four steps pass, a green checkmark appears on the Pull Request, proving the new code safely integrates with the existing codebase.

### Continuous Delivery (CD)

**Continuous Delivery** takes the validated code from the CI process and ensures it is *always ready* to be deployed to the production environment.

The CD phase typically includes:

1. **Packaging:** Bundling the compiled code into a deployable artifact (like a Docker Container image).
2. **Deployment to Staging:** Pushing the artifact to an identical replica of the production environment for QA testers to manually review.
3. **The Manual Gate:** The pipeline pauses. A human manager or QA lead must physically click an "Approve" button to release the code to the live customers.

### Continuous Deployment (CD)

**Continuous Deployment** is the absolute peak of DevOps maturity.
It takes Continuous Delivery one step further by **removing the human manual approval gate entirely**.

If the developer's code passes the CI tests, and the code successfully deploys to the Staging area and passes automated End-to-End (E2E) browser tests, the pipeline automatically, without human intervention, pushes the code straight to the live production servers.

Companies like Netflix and Amazon utilize Continuous Deployment to push thousands of code updates to production every single day.

### Popular CI/CD Tooling

- **GitHub Actions:** Native to GitHub repositories. Uses `.yml` files to define workflows directly in the codebase.
- **Jenkins:** The legacy heavyweight champion. Open-source, incredibly powerful, but requires maintaining a dedicated Jenkins server.
- **GitLab CI:** Built natively into GitLab, highly respected for enterprise scaling.
- **CircleCI / TravisCI:** Cloud-based managed platforms popular in the startup ecosystem.

## Summary

- The **CI/CD Pipeline** is the automated pathway code takes from a developer's commit to the production servers.
- **Continuous Integration (CI):** Automates the building, testing, and security scanning of code to ensure it safely merges into the `main` branch.
- **Continuous Delivery (CD):** Automates the packaging and deployment of code to a Staging environment, stopping at a human approval gate.
- **Continuous Deployment (CD):** Removes all human intervention, automatically deploying code to Production if all automated tests pass.

## Additional Resources

- [Atlassian: CI/CD Pipeline Definitions](https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment)
- [RedHat: What is CI/CD?](https://www.redhat.com/en/topics/devops/what-is-ci-cd)
