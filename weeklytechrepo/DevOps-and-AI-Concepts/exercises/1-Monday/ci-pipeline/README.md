# Lab: Build a CI Pipeline

## The Scenario

You have just joined a team working on a legacy Node.js application (JavaScript). The previous developers never wrote automated tests. You have just convinced management to let you spend a Sprint writing tests, but tests are useless if developers aren't forced to run them.

You need to build a GitHub Actions Continuous Integration (CI) pipeline to enforce code quality on the `main` branch.

## Deliverables

1. Navigate to the `starter_code` directory. Notice there is a generic `app.js` and `package.json` file.
2. In order for GitHub to recognize a workflow, you MUST create the exact directory structure: `.github/workflows/`.
3. Inside that directory, create a new YAML file called `node-ci.yml`.
4. Write the YAML configuration. It must include:
    * A name (e.g., `Node.js CI`).
    * Triggers that fire on pushes or pull requests to the `main` branch.
    * A job that runs on `ubuntu-latest`.
    * A step to checkout the code (`actions/checkout@v3`).
    * A step to setup Node.js (`actions/setup-node@v3`) using node-version `18.x`.
    * A command-line step (`run:`) that executes `npm ci` (to cleanly install dependencies).
    * A command-line step (`run:`) that executes `npm test`.

## Resources

You may consult the official GitHub Actions documentation for Node.js if you get stuck with the exact syntax.

## Definition of Done

- A valid `.github/workflows/node-ci.yml` file exists.
* The YAML syntax is correct and includes all the required steps to successfully test a modern Node.js application.
