package com.leet.medium;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Medium
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 *     1 <= preorder.length <= 3000
 *     inorder.length == preorder.length
 *     -3000 <= preorder[i], inorder[i] <= 3000
 *     preorder and inorder consist of unique values.
 *     Each value of inorder also appears in preorder.
 *     preorder is guaranteed to be the preorder traversal of the tree.
 *     inorder is guaranteed to be the inorder traversal of the tree.
 */

import com.leet.common.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

  /**
   * Standard split and merge solution. To help out with quick look up for inorder root node, we save indexes for all
   * elements in a map or array.
   */

  int[] dp = new int[6000+10];
  int pre = 0;

  public TreeNode buildTree(int[] preorder, int[] inorder, int begin, int end) {
    if (pre >= preorder.length || end < begin) return null;
    int pos = dp[preorder[pre]+3000];
    int leftEnd = pos-1, rightBegin = pos+1;
    var node = new TreeNode(preorder[pre++]);
    node.left = buildTree(preorder, inorder, begin, leftEnd);
    node.right = buildTree(preorder, inorder, rightBegin, end);
    return node;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0;  i < inorder.length; i++) {
      dp[inorder[i]+3000] = i;
    }
    return buildTree(preorder, inorder, 0, inorder.length-1);
  }

  /**
   * Cool O(n) method with no extra space - fastest takes 0 ms
   * pre = [root, left, right]
   * in = [left, root, right]
   *
   * If no left, then:
   * pre = [root, right]
   * in = [root, right]
   * That is, cur pre == cur in
   *
   * If no right, and node == root.left then:
   * pre = [root, node, node.left, root.right]
   * in = [node.left, node, root, root.right]
   * That is, after node in inorder we have root itself (parent in this context) - marks the end of current subtree for
   * the parent
   */
  int[] preorder;
  int[] inorder;
//  int pre; Already defined
  int in;

  TreeNode solve(int parent) {
    if (in >= inorder.length || pre >= preorder.length) return null;
    // The current root
    int val = preorder[pre++];
    var node = new TreeNode(val);
    // If this is the first element in inorder, then there is no left subtree
    if (val != inorder[in]) {
      node.left = solve(val);
    }
    in++;
    if (in != inorder.length && inorder[in] != parent) {
      node.right = solve(parent);
    }

    return node;
  }

  public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
    if (preorder.length == 0) return null;
    this.preorder = preorder;
    this.inorder = inorder;
    in = pre = 0;
    return solve(Integer.MIN_VALUE);
  }
}
