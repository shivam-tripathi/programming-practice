package main

/*
24. Swap Nodes in Pairs
https://leetcode.com/problems/swap-nodes-in-pairs/
Medium

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem
without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

Constraints:
    The number of nodes in the list is in the range [0, 100].
    0 <= Node.val <= 100
*/

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapPairs(head *ListNode) *ListNode {
	sentinel := &ListNode{-1, head}
	node := sentinel
	for node != nil {
		a := node.Next
		var next *ListNode = nil
		if a != nil && a.Next != nil {
			b := a.Next
			next = b.Next
			node.Next = b
			b.Next = a
			a.Next = next
		}
		node = a
	}
	return sentinel.Next
}
