package main

/*
94. Binary Tree Inorder Traversal
https://leetcode.com/problems/binary-tree-inorder-traversal/
Easy

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func inorderTraversal(root *TreeNode) []int {
	ans := []int{}

	stack := []*TreeNode{}
	node := root
	for node != nil {
		stack = append(stack, node)
		node = node.Left
	}

	for len(stack) != 0 {
		node = stack[len(stack)-1]
		stack = stack[:len(stack)-1]

		ans = append(ans, node.Val)

		node = node.Right
		for node != nil {
			stack = append(stack, node)
			node = node.Left
		}
	}

	return ans
}
