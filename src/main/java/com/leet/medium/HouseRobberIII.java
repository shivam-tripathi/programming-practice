package com.leet.medium;

/**
 * 337. House Robber III Medium
 *
 * https://leetcode.com/problems/house-robber-iii/
 *
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree. It
 * will automatically contact the police if two directly-linked houses were
 * broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the
 * thief can rob without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,2,3,null,3,null,1] Output: 7 Explanation: Maximum amount of
 * money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *
 * Input: root = [3,4,5,1,3,null,1] Output: 9 Explanation: Maximum amount of
 * money the thief can rob = 4 + 5 = 9.
 *
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104]. 0 <= Node.val <=
 * 104
 */

import java.util.HashMap;
import java.util.Map;

import com.leet.common.TreeNode;

public class HouseRobberIII {
	public int solve(TreeNode node, Map<TreeNode, Integer> dp) {
		if (node == null)
			return 0;
		if (dp.containsKey(node)) {
			return dp.get(node);
		}
		int without = solve(node.left, dp) + solve(node.right, dp);
		int with = node.val;
		if (node.left != null) {
			with += solve(node.left.left, dp) + solve(node.left.right, dp);
		}
		if (node.right != null) {
			with += solve(node.right.left, dp) + solve(node.right.right, dp);
		}
		dp.put(node, Math.max(with, without));
		return dp.get(node);
	}

	public int rob(TreeNode root) {
		return solve(root, new HashMap<>());
	}
}
