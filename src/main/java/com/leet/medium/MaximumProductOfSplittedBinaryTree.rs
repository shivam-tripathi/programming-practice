/*
1339. Maximum Product of Splitted Binary Tree
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
Medium

Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such
that the product of the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large,
return it modulo 109 + 7.

Note that you need to maximize the answer before taking the mod and not after taking it.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Example 2:
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

Constraints:
  The number of nodes in the tree is in the range [2, 5 * 104].
  1 <= Node.val <= 104
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

static MOD: i64 = 1_000_000_007;

struct Solution {}
impl Solution {
  fn dfs(node_option: &Option<Rc<RefCell<TreeNode>>>, sums: &mut Vec<i64>) -> i64 {
    if let Some(node_rc) = node_option {
      let node = node_rc.as_ref().borrow();
      let sum =
        (node.val as i64) + Solution::dfs(&node.left, sums) + Solution::dfs(&node.right, sums);
      sums.push(sum);
      return sum;
    }
    0
  }

  pub fn max_product(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut sums = Vec::new();
    Self::dfs(&root, &mut sums);
    let mut ans = 0;
    for i in 0..sums.len() {
      let cur = (sums[sums.len() - 1] - sums[i]) * sums[i];
      if ans < cur {
        ans = cur;
      }
    }
    (ans % MOD) as i32
  }
}

fn main() {
  Solution::max_product(None);
}
