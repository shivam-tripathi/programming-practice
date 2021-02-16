package com.leet.medium;
/**
 * 538. Convert BST to Greater Tree
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 */

import com.leet.common.TreeNode;

public class ConvertBSTToGreaterTree {
  public int postOrder(TreeNode node, int sumRight) {
    if (node == null) return sumRight;
    node.val += postOrder(node.right, sumRight);
    return postOrder(node.left, node.val);
  }

  public TreeNode convertBST(TreeNode root) {
    postOrder(root, 0);
    return root;
  }
}
