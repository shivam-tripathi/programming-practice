package com.leet.medium;


/**
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 * Medium
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     -231 <= Node.val <= 231 - 1
 */

/*
Imagine search space to be infinite straight line and each binary tree cutting it into two halves. With each subsequent
cut, the search space is effectively divided into a (min, max) range.

Because it is difficult to represent min (-inf) and max (+inf) at the start, we use null as a placeholder. If
min == null or max == null, it represent -+ inf respectively.
 */

import com.leet.common.TreeNode;

public class ValidateBinarySearchTree {
  public static boolean isValidBST(TreeNode node, Integer min, Integer max) {
    if (node == null) {
      return true;
    }

    boolean isValidNode = (min == null || min.compareTo(node.val) < 0) && (max == null || max.compareTo(node.val) > 0);
    if (isValidNode) {
      return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    return false;
  }

  public static boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  public static void main(String[] args) {
    TreeNode root = TreeNode.construct(0, new Integer[]{2,1,3});
    System.out.println(isValidBST(root));
  }
}

