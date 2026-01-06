class ListNode {
	constructor(public next: ListNode | null) {}
}

function detectCycle(head: ListNode | null): ListNode | null {
  let slow: ListNode | null | undefined = head, fast = head?.next;

  while (slow !== fast && fast && slow) {
    slow = slow.next;
    fast = fast.next?.next;
  }

  if (!fast) return null;

  let first: ListNode | null | undefined = head, second = slow?.next;

  while (first !== second) {
    first = first?.next;
    second = second?.next;
  }

  return first as ListNode | null;
};
