package com.leet.medium;

/**
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Medium
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right,
 * level by level).
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * Input: root = []
 * Output: []
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 2000].
 *     -1000 <= Node.val <= 1000
 */

import com.leet.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) return List.of();
    var ans = new ArrayList<List<Integer>>();
    var curLevel = List.of(root);
    while (curLevel.size() != 0) {
      var nextLevel = new ArrayList<TreeNode>();
      var level = new ArrayList<Integer>();
      for (int i = 0; i < curLevel.size(); i++) {
        var node = curLevel.get(i);
        level.add(node.val);
        if (node.left != null) nextLevel.add(node.left);
        if (node.right != null) nextLevel.add(node.right);
      }
      ans.add(level);
      curLevel = nextLevel;
    }
    return ans;
  }
}
