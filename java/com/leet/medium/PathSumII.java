package com.leet.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 113.
 *
 * Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Medium
 *
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where each path's sum equals targetSum. A leaf is a node
 * with no children.
 *
 * Example 1: Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum =
 * 22 Output: [[5,4,11,2],[5,8,4,5]]
 *
 * Example 2: Input: root = [1,2,3], targetSum = 5 Output: []
 *
 * Example 3: Input: root = [1,2], targetSum = 0 Output: []
 *
 * Constraints: The number of nodes in the tree is in the range [0, 5000]. -1000
 * <= Node.val <= 1000 -1000 <= targetSum <= 1000
 */

import com.leet.common.TreeNode;

public class PathSumII {
  public List<List<Integer>> pathSum(TreeNode node, int target, int sum, List<Integer> path, List<List<Integer>> ans) {
    if (node == null)
      return ans;

    sum += node.val;
    path.add(node.val);

    if (node.left == null && node.right == null) {
      if (sum == target)
        ans.add(new ArrayList<>(path));
    } else {
      pathSum(node.left, target, sum, path, ans);
      pathSum(node.right, target, sum, path, ans);
    }

    path.remove(path.size() - 1);
    return ans;
  }

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    return pathSum(root, targetSum, 0, new ArrayList<>(), new ArrayList<>());
  }
}
