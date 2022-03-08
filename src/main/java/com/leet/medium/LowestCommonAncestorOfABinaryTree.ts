/**
236. Lowest Common Ancestor of a Binary Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
Medium

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a
node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:
		The number of nodes in the tree is in the range [2, 105].
		-109 <= Node.val <= 109
		All Node.val are unique.
		p != q
		p and q will exist in the tree.
 */

class TreeNode {
	val: number
	left: TreeNode | null
	right: TreeNode | null
	constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
		this.val = (val === undefined ? 0 : val)
		this.left = (left === undefined ? null : left)
		this.right = (right === undefined ? null : right)
	}
}

function solve(node: TreeNode, p: TreeNode, q: TreeNode) {
	if (node == null) {
		return { node: null, status: 0 };
	}

	let status = 0;

	if (node === p) {
		status |= 1;
	}

	if (node === q) {
		status |= 2;
	}

	const { node: left, status: leftStatus } = solve(node.left, p, q);

	if (leftStatus == 3) {
		return { node: left, status: 3 };
	}

	status |= leftStatus;

	if (status === 3) {
		return { node, status };
	}

	const { node: right, status: rightStatus } = solve(node.right, p, q);

	if (rightStatus == 3) {
		return { node: right, status: rightStatus };
	}

	status |= rightStatus;
	return { node, status };
}

function lowestCommonAncestor(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	const { node } = solve(root, p, q);
	return node;
};