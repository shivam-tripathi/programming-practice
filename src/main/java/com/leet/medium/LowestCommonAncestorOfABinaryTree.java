package com.leet.medium;

import com.leet.common.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * Constraints:
 *     The number of nodes in the tree is in the range [2, 105].
 *     -109 <= Node.val <= 109
 *     All Node.val are unique.
 *     p != q
 *     p and q will exist in the tree.
 */

public class LowestCommonAncestorOfABinaryTree {
  // Very readable, but makes extra calls even if we have found both the nodes
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root;
  }

  // Checks whether both nodes have been found for each node by using bit operations. Poor in readability, good in performance
  TreeNode ans = null;
  public int dfs(TreeNode node, TreeNode targetA, TreeNode targetB) {
    if (node == null) return 0;
    int status = 0;
    if (node == targetA) status = 1;
    if (node == targetB) status = 2;
    status = status | dfs(node.left, targetA, targetB); // only one of the elements could have been found (if the cur node is one of the targets)
    status = status == 3 ? status : status | dfs(node.right, targetA, targetB); // only run this iteration if required
    if (status == 3 && ans == null) ans = node; // set the ans if we found both the nodes
    return status;
  }
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    dfs(root, p, q);
    return ans;
  }
}
