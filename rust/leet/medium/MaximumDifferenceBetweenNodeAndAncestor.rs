/**
1026. Maximum Difference Between Node and Ancestor
https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
Medium

Given the root of a binary tree, find the maximum value v for which there exist different nodes a
and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an
ancestor of b.

Example 1:
Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:
Input: root = [1,null,2,null,0,3]
Output: 3

Constraints:
  The number of nodes in the tree is in the range [2, 5000].
  0 <= Node.val <= 105Q
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
  fn max_ancestor(
    node_rc: &Option<Rc<RefCell<TreeNode>>>,
    max_value: Option<i32>,
    min_value: Option<i32>,
  ) -> Option<i32> {
    if let Some(node_ref) = node_rc {
      let node = node_ref.as_ref().borrow();
      let mut possible_ans = vec![];
      let mut cur_max = max_value.clone();
      let mut cur_min = min_value.clone();
      if let Some(max) = max_value {
        possible_ans.push((max - node.val).abs());
        if max < node.val {
          cur_max = Some(node.val);
        }
      } else {
        cur_max = Some(node.val);
      }
      if let Some(min) = min_value {
        possible_ans.push((min - node.val).abs());
        if min > node.val {
          cur_min = Some(node.val);
        }
      } else {
        cur_min = Some(node.val);
      }
      if let Some(left_ans) = Solution::max_ancestor(&node.left, cur_max, cur_min) {
        possible_ans.push(left_ans);
      }
      if let Some(right_ans) = Solution::max_ancestor(&node.right, cur_max, cur_min) {
        possible_ans.push(right_ans);
      }
      if let Some(ans) = possible_ans.iter().max() {
        return Some(*ans);
      }
    }
    None
  }

  pub fn max_ancestor_diff(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    if let Some(ans) = Solution::max_ancestor(&root, None, None) {
      return ans;
    }
    0
  }
}

fn main() {}
