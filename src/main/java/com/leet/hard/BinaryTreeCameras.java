package com.leet.hard;

import com.leet.common.TreeNode;

class MinCameraResult {
	int with;
	int without;

	MinCameraResult(int with, int without) {
		this.with = with;
		this.without = without;
	}
}

public class BinaryTreeCameras {
	boolean isLeaf(TreeNode node) {
		return node != null && node.left == null && node.right == null;
	}

	boolean isBaseCase(TreeNode node) {
		if (isLeaf(node))
			return true;
		if (isLeaf(node.left) && isLeaf(node.right))
			return true;
		if (isLeaf(node.left) && node.right == null)
			return true;
		if (isLeaf(node.right) && node.left == null)
			return true;
		return false;
	}

	public void setCamera(TreeNode node, TreeNode parent, int val) {
		if (node.left == null) {
			return;
		}
		node.val = val;
		if (node.left != null) {
			node.val += val;
		}
		if (node.right != null) {
			node.val += val;
		}
		if (parent != null) {
			parent.val += val;
		}
	}

	public int dfs(TreeNode node, TreeNode parent) {
		if (node == null)
			return 0;

		// Continue as it is
		int without = dfs(node.left, node) + dfs(node.right, node);
		if (node.val <= 0) {
			without = Integer.MAX_VALUE;
		}

		// Add a camera
		setCamera(node, parent, 1);
		int with = dfs(node.left, node) + dfs(node.right, node);
		if (with != Integer.MAX_VALUE) {
			with += 1;
		}

		// reset
		setCamera(node, parent, -1);

		return Math.min(with, without);
	}

	public int minCameraCover(TreeNode root) {
		if (isLeaf(root)) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		BinaryTreeCameras camera = new BinaryTreeCameras();
		TreeNode root = TreeNode.construct(0, new Integer[] { 0, 0, null, null, 0, 0, null, null, 0, 0 });
		System.out.println(camera.minCameraCover(root));
	}
}
