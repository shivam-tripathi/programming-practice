package main

/*
515. Find Largest Value in Each Tree Row
https://leetcode.com/problems/find-largest-value-in-each-tree-row/
Medium

Given the root of a binary tree, return an array of the largest value in each row of the tree
(0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]

Constraints:
	The number of nodes in the tree will be in the range [0, 104].
	-231 <= Node.val <= 231 - 1
*/

func largestValuesInEachTreeRow(node *TreeNode, row int, ans []int) []int {
	if node == nil {
		return ans
	}
	if len(ans) <= row {
		ans = append(ans, node.Val)
	} else if ans[row] < node.Val {
		ans[row] = node.Val
	}
	ans = largestValuesInEachTreeRow(node.Left, row+1, ans)
	return largestValuesInEachTreeRow(node.Right, row+1, ans)
}

func largestValues(root *TreeNode) []int {
	return largestValuesInEachTreeRow(root, 0, []int{})
}
