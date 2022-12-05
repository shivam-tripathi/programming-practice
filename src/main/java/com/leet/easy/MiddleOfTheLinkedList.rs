struct Solution {}

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

impl Solution {
  pub fn middle_node(start: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    if start.is_none() {
      return None;
    }

    let head = start.as_ref();

    if head.unwrap().next.is_none() || head.unwrap().next.as_ref().unwrap().next.is_none() {
      return start;
    }

    let mut fast = head;
    let mut slow = head;

    while !fast.is_none() {
      fast = fast.unwrap().next.as_ref();
      if !fast.is_none() {
        fast = fast.unwrap().next.as_ref();
      } else {
        break;
      }
      slow = slow.unwrap().next.as_ref();
    }

    let ans = *slow.unwrap().to_owned();

    Some(Box::new(ans))
  }
}

fn main() {}
