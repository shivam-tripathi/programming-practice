/**
 * Definition for singly-linked list.
 */
class ListNode {
  val: number;
  next: ListNode | null;
  constructor(val?: number, next?: ListNode | null) {
    this.val = val === undefined ? 0 : val;
    this.next = next === undefined ? null : next;
  }
}

export function oddEvenList(head: ListNode | null): ListNode | null {
  const even = new ListNode(0);
  const odd = new ListNode(0);

  let evenIter = even;
  let oddIter = odd;
  let iter = head;
  let isEven = false;

  while (iter) {
    const next = iter.next;
    iter.next = null;
    if (isEven) {
      evenIter.next = iter;
      evenIter = evenIter.next;
    } else {
      oddIter.next = iter;
      oddIter = oddIter.next;
    }
    isEven = !isEven;
    iter = next;
  }

  oddIter.next = even.next;
  return odd.next;
}
