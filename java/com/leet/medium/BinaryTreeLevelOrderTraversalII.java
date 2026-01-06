package com.leet.medium;

/**
 * 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * Medium
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to
 * right, level by level from leaf to root).
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */

import com.leet.common.TreeNode;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {

  // Silly method - basically collecting everything and then reversing it.
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (root == null) return List.of();

    List<List<Integer>> ans = new ArrayList<>();
    var cur = List.of(root);

    while (cur.size() != 0) {
      var vals = new ArrayList<Integer>();
      var next = new ArrayList<TreeNode>();

      for (var node : cur) {
        vals.add(node.val);
        if (node.left != null) next.add(node.left);
        if (node.right != null) next.add(node.right);
      }

      cur = next;
      ans.add(0, vals);
    }

    // Collections.reverse(ans); Gives same speed
    return ans;
  }

  // DFS is always faster than BFS if we are going to traverse through all nodes.
  // With this in mind, we process each level one by one - add elements to the position in array relative to current
  // known depth and level of node in REVERSE order. If some node appears with depth > size of ans, we add a new level
  // the beginning of the ans and add the node there.
  // 0ms 100%
  void solve(TreeNode node, int level, List<List<Integer>> ans) {
    if (node == null) return;
    if (ans.size() < level) ans.add(0, new ArrayList<>());
    ans.get(ans.size() - level).add(node.val);
    solve(node.left, level + 1, ans);
    solve(node.right, level + 1, ans);
  }

  public List<List<Integer>> levelOrderBottomV2(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    solve(root, 1, ans);
    return ans;
  }
}
