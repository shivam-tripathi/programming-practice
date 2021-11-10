package main

/*
101. Symmetric Tree
https://leetcode.com/problems/symmetric-tree/
Easy

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).



Example 1:

Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:

Input: root = [1,2,2,null,3,null,3]
Output: false



Constraints:

    The number of nodes in the tree is in the range [1, 1000].
    -100 <= Node.val <= 100


Follow up: Could you solve it both recursively and iteratively?
*/

type TreeNode struct {
	Left  *TreeNode
	Right *TreeNode
	Val   int
}

// Recursive solution
func sym(a *TreeNode, b *TreeNode) bool {
	if a == nil && b == nil {
		return true
	}
	if a == nil || b == nil || a.Val != b.Val {
		return false
	}
	return sym(a.Left, b.Right) && sym(a.Right, b.Left)
}

// Iterative solution
func iter(root *TreeNode) bool {
	queue := [][2]*TreeNode{}
	queue = append(queue, [2]*TreeNode{root.Left, root.Right})

	for len(queue) != 0 {
		nodes := queue[0]
		a, b := nodes[0], nodes[1]
		queue = queue[1:]

		if a == nil && b != nil {
			return false
		}

		if a != nil && b == nil {
			return false
		}

		if a == nil && b == nil {
			continue
		}

		if a.Val != b.Val {
			return false
		}

		queue = append(queue, [2]*TreeNode{a.Left, b.Right}, [2]*TreeNode{a.Right, b.Left})
	}

	return true
}

func isSymmetric(root *TreeNode) bool {
	// return sym(root.Left, root.Right)
	return iter(root)
}
