package com.leet.medium;

/**
 * 1302. Deepest Leaves Sum
 * https://leetcode.com/problems/deepest-leaves-sum/
 * Medium
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * Example 1:
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 * Example 2:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     1 <= Node.val <= 100
 */

import com.leet.common.TreeNode;

public class DeepestLeavesSum {
  public void solve(TreeNode node, int[] levelDetails, int level) {
    if (node == null) return;
    int maxLevel = levelDetails[0];
    // If this is a leaf
    if (node.left == null && node.right == null) {
      if (level == maxLevel) {
        levelDetails[1] += node.val;
      }
      if (level > maxLevel) {
        levelDetails[0] = level;
        levelDetails[1] = node.val;
      }
      return;
    }
    solve(node.left, levelDetails, level+1);
    solve(node.right, levelDetails, level+1);
  }
  public int deepestLeavesSum(TreeNode root) {
    var ans = new int[2];
    solve(root, ans, 0);
    return ans[1];
  }
}
