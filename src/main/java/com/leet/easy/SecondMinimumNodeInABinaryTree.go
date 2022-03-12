package main

/*
671. Second Minimum Node In a Binary Tree
https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
Easy

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each
node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's
value is the smaller value among its two sub-nodes. More formally, the property root.val =
min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the
nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: root = [2,2,5,null,null,5,7]
Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
Input: root = [2,2,2]
Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

Constraints:
    The number of nodes in the tree is in the range [1, 25].
    1 <= Node.val <= 231 - 1
    root.val == min(root.left.val, root.right.val) for each internal node of the tree.
*/

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func findSecondMinimumValue(node *TreeNode) int {
	if node == nil || node.Left == nil {
		return -1
	}
	base := node.Val
	var lval int
	if node.Left.Val == base {
		lval = findSecondMinimumValue(node.Left)
	} else {
		lval = node.Left.Val
	}

	var rval int
	if node.Right.Val == base {
		rval = findSecondMinimumValue(node.Right)
	} else {
		rval = node.Right.Val
	}

	if lval != -1 && rval != -1 {
		if lval < rval {
			return lval
		}
		return rval
	}

	if lval == -1 {
		return rval
	}

	return lval
}
