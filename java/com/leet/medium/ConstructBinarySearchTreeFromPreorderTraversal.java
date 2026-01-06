package com.leet.medium;

import com.leet.common.TreeNode;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal Medium
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * Given an array of integers preorder, which represents the preorder traversal
 * of a BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree
 * with the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of
 * Node.left has a value strictly less than Node.val, and any descendant of
 * Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first,
 * then traverses Node.left, then traverses Node.right.
 *
 *
 *
 * Example 1:
 *
 * Input: preorder = [8,5,1,7,10,12] Output: [8,5,10,1,7,null,12]
 *
 * Example 2:
 *
 * Input: preorder = [1,3] Output: [1,null,3]
 *
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 100 1 <= preorder[i] <= 1000 All the values of
 * preorder are unique.
 *
 *
 */

/**
 * This captures essense of a range in numeric space
 */
class Range {
	Integer low;
	Integer high;

	Range(Integer low, Integer high) {
		this.low = low;
		this.high = high;
	}

	boolean isInLimits(Integer num) {
		if (low != null && num.compareTo(this.low) < 0) {
			return false;
		}
		if (high != null && num.compareTo(this.high) > 0) {
			return false;
		}
		return true;
	}
}

/**
 * Helper class to encapsulate the response from bstFromPreorder function. It
 * captures the root of the subtree and the last index which was utilised in
 * buildin this subtree
 */
class BstFromPreorderResponse {
	TreeNode node;
	int lastIdx;

	BstFromPreorderResponse(TreeNode node, Integer lastIdx) {
		this.node = node;
		this.lastIdx = lastIdx;
	}
}

public class ConstructBinarySearchTreeFromPreorderTraversal {
	private BstFromPreorderResponse bstFromPreorder(int[] preorder, int cur, Range range) {
		// If this index is out of bounds or this number is not in range for this
		// subtree, we backtrack
		if (cur >= preorder.length || !range.isInLimits(preorder[cur])) {
			return new BstFromPreorderResponse(null, cur - 1);
		}
		int elem = preorder[cur];
		// Construct left subtree from cur+1
		BstFromPreorderResponse left = bstFromPreorder(preorder, cur + 1, new Range(range.low, elem));
		// Construct right subtree from left.lastIdx + 1
		BstFromPreorderResponse right = bstFromPreorder(preorder, left.lastIdx + 1, new Range(elem, range.high));
		// Construct the current node
		TreeNode node = new TreeNode(elem, left.node, right.node);
		return new BstFromPreorderResponse(node, right.lastIdx);
	}

	public TreeNode bstFromPreorder(int[] preorder) {
		BstFromPreorderResponse value = bstFromPreorder(preorder, 0, new Range(null, null));
		return value.node;
	}
}
