/*
872. Leaf-Similar Trees
https://leetcode.com/problems/leaf-similar-trees/description/
Easy

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form
a leaf value sequence.

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input:
root1 = [3,5,1,6,2,9,8,null,null,7,4],
root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input:
root1 = [1,2,3],
root2 = [1,3,2]
Output: false

Constraints:
  The number of nodes in each tree will be in the range [1, 200].
  Both of the given trees will have values in the range [0, 200].
*/

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

struct Solution {}

impl Solution {
  fn get_leaves(node_option: &Option<Rc<RefCell<TreeNode>>>, leaves: &mut Vec<i32>) {
    if let Some(node) = node_option {
      let ref_node = node.as_ref().borrow();
      if ref_node.left.is_none() && ref_node.right.is_none() {
        leaves.push(node.as_ref().borrow().val);
      } else {
        Solution::get_leaves(&ref_node.left, leaves);
        Solution::get_leaves(&ref_node.right, leaves);
      }
    }
  }

  pub fn leaf_similar(
    root1: Option<Rc<RefCell<TreeNode>>>,
    root2: Option<Rc<RefCell<TreeNode>>>,
  ) -> bool {
    let mut leaves_1 = vec![];
    let mut leaves_2 = vec![];
    Solution::get_leaves(&root1, &mut leaves_1);
    Solution::get_leaves(&root2, &mut leaves_2);
    if leaves_1.len() != leaves_2.len() {
      return false;
    }
    for i in 0..leaves_1.len() {
      if leaves_1[i] != leaves_2[i] {
        return false;
      }
    }
    true
  }
}

fn main() {}
