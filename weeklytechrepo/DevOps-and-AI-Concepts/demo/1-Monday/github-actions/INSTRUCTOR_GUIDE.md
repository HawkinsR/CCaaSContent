# Demo: Continuous Integration with GitHub Actions

## Phase 1: The Concept

**Time:** 10 mins

1. **Context:** Developers push code to GitHub. Historically, QA teams would then manually download the code and run tests to see if the developer broke anything.
2. **Continuous Integration (CI):** The practice of automating those tests. Every single time a developer pushes code, a robot (a CI Server) downloads the code, builds it, and runs the tests. If the tests fail, the pull request cannot be merged.
3. **GitHub Actions:** GitHub's built-in CI/CD platform. It looks for a specific folder (`.github/workflows`) and reads YAML files to know what commands to run.

## Phase 2: Building the Workflow (Live Implementation)

**Time:** 15 mins

1. Open `code/.github/workflows/ci.yml`.
2. **Walkthrough the YAML:**
    * **`name`**: The display name in the GitHub Actions tab.
    * **`on`**: The trigger. Show how we configure it to run on `push` and `pull_request` to the `main` branch.
    * **`jobs`**: The actual work to be done. We define a job called `build-and-test`.
    * **`runs-on`**: Explain "Runners". GitHub creates a fresh, temporary Ubuntu Linux virtual machine in the cloud just for this job.
    * **`steps`**: The sequence of commands executed on that runner.
        * Point out `actions/checkout@v3` (This downloads the repository code onto the runner).
        * Point out `actions/setup-python@v4` (Installs Python on the runner).
        * Point out the `run` commands where we physically execute `pip install` and `pytest`.

## Phase 3: The Result

*Instructor Note: If you have a live GitHub repository available, commit this file and show the Actions tab executing the workflow green.*
