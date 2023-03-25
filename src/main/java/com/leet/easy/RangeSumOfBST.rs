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
use std::borrow::Borrow;
use std::cell::RefCell;
use std::rc::Rc;

struct Solution {}

impl Solution {
  pub fn range_sum_bst_util(root: &Option<Rc<RefCell<TreeNode>>>, low: i32, high: i32) -> i32 {
    if let Some(node) = root {
      let bnode = node.as_ref().borrow();
      if bnode.val < low {
        return Solution::range_sum_bst_util(&bnode.right, low, high);
      }
      if bnode.val > high {
        return Solution::range_sum_bst_util(&bnode.left, low, high);
      }
      return bnode.val
        + Solution::range_sum_bst_util(&bnode.right, low, high)
        + Solution::range_sum_bst_util(&bnode.left, low, high);
    }
    0
  }

  pub fn range_sum_bst(root: Option<Rc<RefCell<TreeNode>>>, low: i32, high: i32) -> i32 {
    Solution::range_sum_bst_util(&root, low, high)
  }
}

fn main() {}
