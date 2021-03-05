package com.leet.easy;

/**
 * 637. Average of Levels in Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * Easy
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 *     The range of node's value is in the range of 32-bit signed integer.
 */

import com.leet.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AverageOfLevelsInBinaryTree {
  // BFS. DFS is almost always faster than BFS, even though it is iterative.
  public List<Double> averageOfLevels(TreeNode root) {
    var avg = new ArrayList<Double>();
    var items = List.of(root);
    while (items.size() != 0) {
      double sum = 0;
      var nextLevel = new ArrayList<TreeNode>();
      for (TreeNode node: items) {
        sum += node.val;
        if (node.left != null) nextLevel.add(node.left);
        if (node.right != null) nextLevel.add(node.right);
      }
      // if (nextLevel.size() != 0) queue.add(nextLevel);
      avg.add(sum / items.size());
      items = nextLevel;
    }
    return avg;
  }

  void dfs(TreeNode node, List<Double> sum, List<Integer> count, int depth) {
    if (node == null) return;
    if (sum.size() < depth + 1) {
      sum.add(0d + node.val);
      count.add(1);
    } else {
      sum.set(depth, sum.get(depth) + node.val);
      count.set(depth, count.get(depth) + 1);
    }

    dfs(node.left, sum, count, depth + 1);
    dfs(node.right, sum, count, depth + 1);
  }

  // DFS
  public List<Double> averageOfLevels_2(TreeNode root) {
    var sum = new ArrayList<Double>();
    var count = new ArrayList<Integer>();
    dfs(root, sum, count, 0);

    for (int i = 0; i < sum.size(); i++) {
      sum.set(i, sum.get(i) / count.get(i));
    }

    return sum;
  }
}
