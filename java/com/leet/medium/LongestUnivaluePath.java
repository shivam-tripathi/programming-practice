package com.leet.medium;

/**
 * 687. Longest Univalue Path Medium
 *
 * https://leetcode.com/problems/longest-univalue-path/
 *
 * Given the root of a binary tree, return the length of the longest path, where
 * each node in the path has the same value. This path may or may not pass
 * through the root.
 *
 * The length of the path between two nodes is represented by the number of
 * edges between them.
 *
 * Example 1: Input: root = [5,4,5,1,1,5] Output: 2
 *
 * Example 2: Input: root = [1,4,5,4,4,5] Output: 2
 *
 * Constraints: The number of nodes in the tree is in the range [0, 104]. -1000
 * <= Node.val <= 1000 The depth of the tree will not exceed 1000.
 */

import com.leet.common.TreeNode;

public class LongestUnivaluePath {
	private int[] solve(TreeNode node) {
		if (node == null) {
			return new int[] { 0, 0 };
		}
		int[] leftRes = solve(node.left);
		int[] rightRes = solve(node.right);
		int leftChain = 0, rightChain = 0;
		if (node.left != null && node.left.val == node.val) {
			leftChain = leftRes[0];
		}
		if (node.right != null && node.right.val == node.val) {
			rightChain = rightRes[0];
		}
		int chain = Math.max(leftChain + 1, rightChain + 1);
		int including = 1 + leftChain + rightChain;
		return new int[] { chain, Math.max(including, Math.max(leftRes[1], rightRes[1])) };
	}

	public int longestUnivaluePath(TreeNode root) {
		int[] ans = solve(root);
		return Math.max(0, ans[1] - 1);
	}
}