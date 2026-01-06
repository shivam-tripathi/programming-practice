/**
328. Odd Even Linked List
https://leetcode.com/problems/odd-even-linked-list/
Medium

Given the head of a singly linked list, group all the nodes with odd indices together followed by
the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as it was in the
input.
You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Example 1:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

Example 2:
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]

Constraints:
  The number of nodes in the linked list is in the range [0, 104].
  -106 <= Node.val <= 106
 */

// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
  pub val: i32,
  pub next: Option<Box<ListNode>>,
}

impl ListNode {
  #[inline]
  fn new(val: i32) -> Self {
    ListNode { next: None, val }
  }
}

struct Solution {}

impl Solution {
  pub fn odd_even_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut even = ListNode::new(0);
    let mut odd = ListNode::new(0);

    let mut even_iter = &mut even;
    let mut odd_iter = &mut odd;

    let mut is_even = false;
    let mut iter = head;

    while let Some(mut node) = iter {
      iter = std::mem::replace(&mut node.next, None);
      if is_even {
        even_iter.next = Some(node);
        even_iter = even_iter.next.as_mut().unwrap();
      } else {
        odd_iter.next = Some(node);
        odd_iter = odd_iter.next.as_mut().unwrap();
      }
      is_even = !is_even;
    }

    odd_iter.next = even.next;
    odd.next
  }
}

fn main() {}
