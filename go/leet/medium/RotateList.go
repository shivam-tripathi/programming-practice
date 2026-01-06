package main

/*
61. Rotate List

https://leetcode.com/problems/rotate-list/

Medium

Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:
	The number of nodes in the list is in the range [0, 500].
	-100 <= Node.val <= 100
	0 <= k <= 2 * 109
*/

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil {
		return head
	}

	size, node := 1, head
	for node.Next != nil {
		node = node.Next
		size += 1
	}

	shift := k % size
	if shift == 0 {
		return head
	}

	node.Next = head
	node = head

	for shift < size-1 {
		node = node.Next
		shift += 1
	}

	head = node.Next
	node.Next = nil

	return head
}
