package com.leet.medium;

import com.leet.common.TreeNode;

public class TwoSumBST {
	boolean solve(TreeNode nodeA, TreeNode nodeB, int target) {
		if (nodeA == null || nodeB == null) {
			return false;
		}
		int sum = nodeA.val + nodeB.val;
		if (sum > target) {
			return solve(nodeA, nodeB.left, target) || solve(nodeA.left, nodeB, target);
		}
		return solve(nodeA, nodeB.right, target) || solve(nodeA.right, nodeB, target);
	}

	public static void main(String[] args) {
		for (int[][] inp : new int[][][] { { { 2, 1, 3 }, { 1, 0, 3 }, { 5 } },
				{ { 0, -10, 10 }, { 5, 1, 7, 0, 2 }, { 18 } }, }) {
			TreeNode nodeA = TreeNode.create(inp[0]);
			TreeNode nodeB = TreeNode.create(inp[1]);
			System.out.println(nodeA);
			System.out.println(nodeB);
			System.out.println(new TwoSumBST().solve(nodeA, nodeB, inp[2][0]));
		}
	}
}
