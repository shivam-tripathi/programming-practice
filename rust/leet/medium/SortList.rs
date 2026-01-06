// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
struct ListNode {
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
  pub fn sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let sentinel = ListNode::new(0);

    let mut traverse = &mut head;

    while traverse.is_some() {
      let mut node = &sentinel.next;
      let mut prev = &sentinel;
      let ref_traverse = traverse.as_ref().unwrap();
      while node.is_some() {
        let ref_node = node.as_ref().unwrap();
        if ref_node.val < ref_traverse.val {
          break;
        }
        prev = ref_node;
        node = &ref_node.next;
      }
      let tmp = ref_traverse;
      prev.next = {..*traverse};
      // traverse.as_mut().unwrap().next = node;
      // traverse = &mut tmp.next;
      // prev.next = ref_traverse;
    }
    sentinel.next
  }
}

fn main() {}
