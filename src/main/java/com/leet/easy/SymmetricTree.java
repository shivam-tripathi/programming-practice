package com.leet.easy;

import com.leet.common.TreeNode;

import java.util.ArrayList;

/**
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 * Easy
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 * Follow up: Could you solve it both recursively and iteratively?
 */
public class SymmetricTree {
  public boolean isSymmtericIterative(TreeNode root) {
    if (root == null) return true;
    var cur = new ArrayList<TreeNode[]>();
    cur.add(new TreeNode[]{root.left, root.right});
    while (cur.size() != 0) {
      var next = new ArrayList<TreeNode[]>();
      for (var nodes : cur) {
        TreeNode a = nodes[0], b = nodes[1];
        if (a == null && b == null) continue;
        if (a == null || b == null || a.val != b.val) return false;
        next.add(new TreeNode[]{a.left, b.right});
        next.add(new TreeNode[]{a.right, b.left});
      }
      cur = next;
    }
    return true;
  }

  public boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null || left.val != right.val) return false;
    return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isSymmetric(root.left, root.right);
  }
}
