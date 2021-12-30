package main

/**
 * 116. Populating Next Right Pointers in Each Node
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * Medium
 *
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following
 * definition:
 *
 * struct Node { int val; Node *left; Node *right; Node *next; }
 *
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL. Initially, all next
 * pointers are set to NULL.
 *
 * Example 1: Input: root = [1,2,3,4,5,6,7] Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function
 * should populate each next pointer to point to its next right node, just like
 * in Figure B. The serialized output is in level order as connected by the next
 * pointers, with '#' signifying the end of each level.
 *
 * Example 2: Input: root = [] Output: []
 *
 * Constraints: The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 * Follow-up: You may only use constant extra space. The recursive approach is
 * fine. You may assume implicit stack space does not count as extra space for
 * this problem.
 *
 */

/**
Definition for a NodeWithNext.
*/

type NodeWithNext struct {
	Val   int
	Left  *NodeWithNext
	Right *NodeWithNext
	Next  *NodeWithNext
}

// O(1) space except for the recursive calls
func connectRecur(left, right *NodeWithNext) {
	if left == nil {
		return
	}
	left.Next = right
	connectRecur(left.Right, right.Left)
	connectRecur(left.Left, left.Right)
	connectRecur(right.Left, right.Right)
}

// fast iterative solution with O(2^h) space
func connect(root *NodeWithNext) *NodeWithNext {
	if root == nil {
		return root
	}
	cur := []*NodeWithNext{root}
	for len(cur) != 0 {
		next := []*NodeWithNext{}
		var prev *NodeWithNext = nil
		for _, node := range cur {
			if node != nil {
				if prev != nil {
					prev.Next = node
				}
				prev = node
				next = append(next, node.Left, node.Right)
			}
		}
		cur = next
	}
	return root
}

// Experimental solution for not a perfect binary tree (question is about perfect binary tree though)
func expRecurSol(left, right *NodeWithNext) {
	if left != nil && right != nil {
		left.Next = right
		orders := [][]*NodeWithNext{
			{left.Right, right.Left},
			{left.Left, right.Left},
			{left.Right, right.Right},
			{left.Left, right.Right},
		}

		for _, order := range orders {
			a, b := order[0], order[1]
			if a != nil && b != nil {
				expRecurSol(a, b)
				break
			}
		}
	}

	if left != nil {
		expRecurSol(left.Left, left.Right)
	}
	if right != nil {
		expRecurSol(right.Left, right.Right)
	}
}
