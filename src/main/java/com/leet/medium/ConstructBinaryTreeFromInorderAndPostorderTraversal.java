package com.leet.medium;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Medium
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 *     1 <= inorder.length <= 3000
 *     postorder.length == inorder.length
 *     -3000 <= inorder[i], postorder[i] <= 3000
 *     inorder and postorder consist of unique values.
 *     Each value of postorder also appears in inorder.
 *     inorder is guaranteed to be the inorder traversal of the tree.
 *     postorder is guaranteed to be the postorder traversal of the tree.
 */

import com.leet.common.TreeNode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  int[] inorder;
  int[] postorder;

  // Pointer to inorder element
  int in;
  // Pointer to postorder element
  int post;

  TreeNode solve(int parent) {
    // If inorder is finished or postorder is finished, return
    if (in < 0 || post < 0) return null;
    // Get the current post order value - this is the root for this subtree
    int val = postorder[post--];

    TreeNode node = new TreeNode(val);

    // If this is the first element in inorder - there is no right subtree
    // Else we recur till we hit the leaf
    if (inorder[in] != val) {
      node.right = solve(val);
    }

    // If the traversal is correct, (val == inorder[in]) should hold.
    // We acknowledge this and move to the next element
    in--;

    // If next element in inorder is actually the grandest parent - there is no left subtree
    // Else we recur
    if (in != -1 && inorder[in] != parent) {
      node.left = solve(parent);
    }

    return node;
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    // If traversal is empty return null
    if (inorder.length==0) return null;

    this.inorder = inorder;
    this.postorder = postorder;
    // We move from right to left
    this.post = this.in = postorder.length - 1;

    // Default guard value is inf
    return solve(Integer.MAX_VALUE);
  }
}
