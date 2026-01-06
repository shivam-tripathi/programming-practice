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

use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
  pub fn solve(node: &Option<Rc<RefCell<TreeNode>>>, level: usize, levels: &mut Vec<Option<i32>>) {
    if let Some(n) = node {
      if levels[level].is_none() {
        levels[level] = Some(n.borrow().val);
      }
      Solution::solve(&n.borrow().right, level + 1, levels);
      Solution::solve(&n.borrow().left, level + 1, levels);
    }
  }

  pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
    let mut levels = vec![None; 100];
    Solution::solve(&root, 0, &mut levels);
    levels
      .iter()
      .filter(|e| e.is_some())
      .map(|e| e.unwrap())
      .collect::<Vec<i32>>()
  }
}
