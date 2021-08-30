package main

/*
148. Sort List
https://leetcode.com/problems/sort-list/
Medium

Given the head of a linked list, return the list after sorting it in ascending order.
Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []

Constraints:
    The number of nodes in the list is in the range [0, 5 * 104].
    -105 <= Node.val <= 105
*/

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func sortList(head *ListNode) *ListNode {
	slow, fast := head, head
	var prev *ListNode
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		prev = slow
		slow = slow.Next
	}

	if fast == slow {
		return head
	}

	otherHead := slow
	if prev != nil {
		prev.Next = nil
	}

	sortedA, sortedB := sortList(head), sortList(otherHead)

	sentinel := &ListNode{}
	node := sentinel

	insAsTail := func(a *ListNode, b *ListNode) (*ListNode, *ListNode) {
		a.Next = b
		b = b.Next
		a = a.Next
		return a, b
	}

	for sortedA != nil && sortedB != nil {
		if sortedA.Val < sortedB.Val {
			node, sortedA = insAsTail(node, sortedA)
		} else {
			node, sortedB = insAsTail(node, sortedB)
		}
	}

	for sortedA != nil {
		node, sortedA = insAsTail(node, sortedA)
	}

	for sortedB != nil {
		node, sortedB = insAsTail(node, sortedB)
	}

	return sentinel.Next
}
