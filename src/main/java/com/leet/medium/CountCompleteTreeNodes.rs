use std::cell::RefCell;
use std::rc::Rc;

// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
  pub val: i32,
  pub left: Option<Rc<RefCell<TreeNode>>>,
  pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
  #[inline]
  pub fn new(val: i32) -> Self {
    TreeNode {
      val,
      left: None,
      right: None,
    }
  }

  pub fn from_list(arr: &Vec<i32>, idx: usize) -> Option<Rc<RefCell<TreeNode>>> {
    if idx >= arr.len() {
      return None;
    }
    let mut node = TreeNode::new(arr[idx]);
    node.left = TreeNode::from_list(arr, 2 * idx + 1);
    node.right = TreeNode::from_list(arr, 2 * idx + 1);
    Some(Rc::new(RefCell::new(node)))
  }
}

struct Solution {}

impl Solution {
  pub fn count_nodes(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let (ans, _) = Solution::solve(root, 0);
    ans
  }
}

fn main() {
  let root = TreeNode::from_list(&vec![1, 2, 3, 4, 5, 6], 0);
  println!("{}", Solution::count_nodes(root));
}
