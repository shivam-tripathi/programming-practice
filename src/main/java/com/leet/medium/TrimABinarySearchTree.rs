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
}

impl Solution {
  pub fn trim_bst(
    root: Option<Rc<RefCell<TreeNode>>>,
    low: i32,
    high: i32,
  ) -> Option<Rc<RefCell<TreeNode>>> {
    return match (root) {
      None => root,
      Some(node_ref) => {
        let mut node = node_ref.borrow();
        if node.val < low {
          return Solution::trim_bst(node.right.clone(), low, high);
        }
        if node.val > high {
          return Solution::trim_bst(node.left.clone(), low, high);
        }
        let ans = TreeNode {
          val: node.val,
          left: Solution::trim_bst(node.left.clone(), low, high),
          right: Solution::trim_bst(node.right.clone(), low, high),
        };
        return Some(Rc::new(RefCell::new(ans)));
      }
    };
  }
}
