package com.leet.medium;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Medium
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * Input: root = []
 * Output: []
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 2000].
 *     -100 <= Node.val <= 100
 */

import com.leet.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    var cur = List.of(root);
    boolean zigzag = false;

    while (!cur.isEmpty()) {
      var next = new ArrayList<TreeNode>();
      Integer[] add = new Integer[cur.size()];
      int pos = zigzag ? cur.size() - 1 : 0;
      for (TreeNode node : cur) {
        if (zigzag) add[pos--] = node.val;
        else add[pos++] = node.val;
        if (node.left != null) {
          next.add(node.left);
        }
        if (node.right != null) {
          next.add(node.right);
        }
      }
      ans.add(Arrays.asList(add));
      cur = next;
      zigzag = !zigzag;
    }
    return ans;
  }
}
