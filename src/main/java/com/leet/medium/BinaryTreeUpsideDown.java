package com.leet.medium;

import com.leet.common.TreeNode;

/**
 * 156 Binary Tree Upside Down
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes.
 *
 * Return the new root.
 *
 * Example:
 *
 * Input: [1,2,3,4,5]
 *
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 *
 * Clarification: Confused what [4,5,2,#,#,3,1] means? Read more below on how
 * binary tree is serialized on OJ.
 *
 * The serialization of a binary tree follows a level order traversal, where '#'
 * signifies a path terminator where no node exists below.
 *
 */

public class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode node, TreeNode sentinel) {
		if (node == null) {
			return node;
		}

		// the right nodes are either leaf nodes with a sibling (a left node that shares
		// the same parent node) or empty. So if left doesn't exist, it is guaranteed
		// right will also not exist. Current node's parent is the left child. If
		// there's not parent, then it becomes the new root by default.
		TreeNode parent = upsideDownBinaryTree(node.left, sentinel);

		if (parent == null) {
			if (sentinel.left == null) {
				sentinel.left = node;
			}
			System.out.println("parent is null and new root is " + node.val + " sentinel.left " + sentinel.left);
			// no right node, so we can skip processing it
			return node;
		} else {
			parent.right = node;
			parent.left = upsideDownBinaryTree(node.right, sentinel);
			node.right = node.left = null;
			System.out.println(parent.val);
			// System.out.println("parent: " + parent.val + " parent.right " + node.val + "
			// parent.left " + parent.left);
			return parent.left;
		}
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.create(new int[] { 1, 2, 3, 4, 5 });
		TreeNode sentinel = new TreeNode();
		TreeNode newRoot = new BinaryTreeUpsideDown().upsideDownBinaryTree(root, sentinel);
		System.out.println(newRoot.left);
	}
}
