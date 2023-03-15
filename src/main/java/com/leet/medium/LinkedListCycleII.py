from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None

        slow, fast = head, head
        while fast and fast.next:
            slow = slow.next if slow else None
            fast = fast.next.next
            if slow == fast:
                break

        if not fast or not fast.next:
            return None

        start = head
        while slow != start:
            start = start.next if start else None
            slow = slow.next if slow else None

        return start
