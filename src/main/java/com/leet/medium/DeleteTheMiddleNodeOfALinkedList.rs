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
  pub fn delete_middle(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut size = 0;
		let mut
    let mut node = head;
    while let Some(cur) = node {
      size += 1;
      node = cur.next;
    }
    let mid = size / 2;
    let mut sentinel = Some(Box::new(ListNode::new(0)));
    sentinel.unwrap().as_mut().next = head;
    let mut prev = sentinel;
    let mut cur = head;
    let idx = 0;
    while idx < mid {
      let next = cur.unwrap().next;
      prev = cur;
      cur = next;
    }
    if let Some(cur_node) = cur {
      prev.unwrap().next = cur_node.next;
    } else {
      prev.unwrap().next = None;
    }
    sentinel.unwrap().next
  }
}

fn main() {}
