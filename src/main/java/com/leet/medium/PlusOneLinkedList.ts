import { assertEquals } from "https://deno.land/std@0.119.0/testing/asserts.ts";

class ListNode {
  val: number;
  next: ListNode | null;
  constructor(val: number) {
    this.val = val;
    this.next = null;
  }

  static from(arr: number[]) {
    const sentinel = new ListNode(0);
    let curr = sentinel;
    for (const val of arr) {
      curr.next = new ListNode(val);
      curr = curr.next;
    }
    return sentinel.next;
  }

  static toList(node: ListNode | null) {
    const arr: number[] = [];
    while (node) {
      arr.push(node.val);
      node = node.next;
    }
    return arr;
  }
}

// we use base to identify the base carry
function solve(node: ListNode | null, base: number): number {
  if (node === null) {
    return base;
  }
  const orig = node.val;
  const prev = solve(node.next, base);
  node.val += prev;
  let carry = 0;
  if (node.val < 0) {
    node.val = 9;
    carry = -1;
  } else {
    carry = Math.floor(Math.abs(node.val) / 10);
    node.val %= 10;
  }
  // console.log({ orig, prev, val: node.val, carry });
  return carry;
}

function plusOne(head: ListNode | null): ListNode | null {
  if (head) {
    // For negative numbers, we need to subtract 1 from abs value
    const base = head.val >= 0 ? 1 : -1;
    // We are dealing with just positive numbers
    head.val = Math.abs(head.val);
    // Is there any leftover carry?
    const carry = solve(head, base);
    if (carry !== 0) {
      const newHead = new ListNode(carry);
      newHead.next = head;
      head = newHead;
    }
    // Remove the trailing zeros
    while (head && head.val === 0 && head.next !== null) {
      head = head.next;
    }
    // Revert back to negative if needed (-0 and 0 are not same for whatever reasons)
    if (base < 0 && head && head.val) {
      head.val = -head.val;
    }
  }
  return head;
}

Deno.test({
  name: "plusOne",
  fn: () => {
    const runner = (arr: number[], expected: number[]) => {
      // console.log({ arr, expected });
      const node = ListNode.from(arr);
      const result = plusOne(node);
      const list = ListNode.toList(result);
      // console.log({ arr, list, expected });
      assertEquals(list, expected);
    };

    runner([1, 2, 3], [1, 2, 4]);
    runner([9, 9, 9], [1, 0, 0, 0]);
    runner([1, 2, 3, 4, 5], [1, 2, 3, 4, 6]);
    runner([], []);
    runner([0], [1]);
    runner([9], [1, 0]);
    runner([-1, 2, 3], [-1, 2, 2]);
    runner([-1], [0]);
    runner([-1, 0], [-9]);
    runner([-1], [0]);
    runner([-1, 0, 0], [-9, 9]);
    runner([0], [1]);
  },
});
