package com.leet.easy;

/**
 * 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * Easy
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 105].
 *     -1000 <= Node.val <= 1000
 */

import com.leet.common.TreeNode;

import java.util.LinkedList;

public class MinimumDepthOfBinaryTree {
  public int minDepth(TreeNode root) {
    if (root == null) return 0;

    var queue = new LinkedList<TreeNode>();
    var depths = new LinkedList<Integer>();
    queue.add(root);
    depths.add(1);

    while (queue.size() > 0) {
      var node = queue.remove();
      var depth = depths.remove();
      if (node == null) continue;
      if (node.left == null && node.right == null) return depth;

      queue.add(node.left);
      depths.add(depth + 1);
      queue.add(node.right);
      depths.add(depth + 1);
    }
    return 0;
  }
}
