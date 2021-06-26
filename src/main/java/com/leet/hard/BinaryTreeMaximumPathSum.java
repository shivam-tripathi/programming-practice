package com.leet.hard;

/**
 * 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Hard
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
 * through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 3 * 104].
 *     -1000 <= Node.val <= 1000
 */

import com.leet.common.TreeNode;

public class BinaryTreeMaximumPathSum {
  int max = -5000;

  public int solve(TreeNode node) {
    if (node == null) {
      return -5000;
    }

    int left = solve(node.left);
    int right = solve(node.right);

    int terminating = Math.max(node.val, node.val + Math.max(left, right));
    int leftToRight = node.val + left + right;
    max = Math.max(terminating, Math.max(leftToRight, max));
    return terminating;
  }

  public int maxPathSum(TreeNode root) {
    solve(root);
    return max;
  }
}
