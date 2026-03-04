# Demo: Handling Files and I/O Exceptions

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Thus far, every variable we've made vanished the microsecond the terminal closed. If we want permanent persistence, we must interact with the hard drive.
2. **Context Managers (`with` block):** We must always *close* a file after opening it to prevent memory leaks and file locks. The Pythonic way to do this is using the `with` keyword, which guarantees closure even if the script crashes halfway through reading it.
3. **Exception Handling (`try-except`):** Expecting to read a file that doesn't exist is a classic guarantee of a crash.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/io_exceptions.py`.
2. **Walkthrough the Code:**
    * Show writing to the file first (`"w"` mode = write/overwrite).
    * Show reading from the file (`"r"` mode = read). Emphasize `.read()` returning a massive String and `.readlines()` returning a List of Strings.
    * **The Intentional Error:** Create a block that attempts to read a completely fake, non-existent file name.
    * Demonstrate wrapping that block in a strictly targeted `except FileNotFoundError as e:` catching mechanism.
    * Show the `finally:` block and explain how that is mathematically guaranteed to run regardless of the crash.
3. **Run the App:**
    * Execute the script. Watch the physical `.txt` file appear in the directory.
    * Watch the application gracefully catch the phantom file without screaming a massive red stack trace, executing the `finally` block successfully.
