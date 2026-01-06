package com.leet.easy;

/**
 * 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 * Easy
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * A binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * Input: root = []
 * Output: true
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */

import com.leet.common.TreeNode;

public class BalancedBinaryTree {
  int isBalancedUtil(TreeNode node) {
    if (node == null) return 0;
    int left = isBalancedUtil(node.left);
    if (left == -1) return -1;
    int right = isBalancedUtil(node.right);
    if (right == -1) return -1;
    if (Math.abs(left - right) > 1) return -1;
    return 1 + Math.max(left, right);
  }

  public boolean isBalanced(TreeNode root) {
    return isBalancedUtil(root) != -1;
  }
}
