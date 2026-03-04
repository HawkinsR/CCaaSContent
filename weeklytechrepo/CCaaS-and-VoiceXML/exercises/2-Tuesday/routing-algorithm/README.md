# Lab: Priority-Based Routing Algorithm

## The Scenario

Your company offers a premium "Platinum" subscription tier. When Platinum members call Support, they are absolutely forbidden from waiting in the queue behind regular "Standard" members, even if the Standard member called first.

You are tasked with upgrading the ACD (Automatic Contact Distributor) engine to support Priority Routing.

## Deliverables

1. Navigate to the `starter_code` directory and open `queue.py`.
2. Review the `Call` object. It now has a `priority` attribute (an integer). A higher number means higher priority. Platinum calls have priority `100`; Standard calls have priority `1`.
3. Review the `tick()` method. This simulates one second of real-world time in the Contact Center.
4. **The Challenge:** Implement the `add_to_queue(call)` logic so that the `self.queue` list is strictly ordered by priority (highest first). If two calls have the exact same priority, the older call (the one added first) should remain in front.
5. In Python, the `list.sort()` method is *stable*, meaning it preserves the original order of items that compare equal. This is heavily advantageous for queue mechanics! You can also look into standard library tools like `heapq` or `queue.PriorityQueue` if you prefer to rewrite the data structure.

## Definition of Done

- When you run the script, the `tick()` loop prints out the processing order of the calls.
- Charlie (Platinum) and Eve (Platinum) MUST be processed before Alice, Bob, and Dave (Standard).
- Between Charlie and Eve, Charlie must be processed first because he called first.
- Between Alice, Bob, and Dave, they must be processed in the exact order they called.
