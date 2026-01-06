package main

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	carry := 0
	sentinel := &ListNode{0, nil}
	prev := sentinel

	for l1 != nil && l2 != nil {
		sum := l1.Val + l2.Val + carry
		carry, sum = sum/10, sum%10
		prev.Next = &ListNode{sum, nil}
		prev = prev.Next
		l1, l2 = l1.Next, l2.Next
	}

	for l1 != nil {
		sum := l1.Val + carry
		carry, sum = sum/10, sum%10
		prev.Next = &ListNode{sum, nil}
		prev = prev.Next
		l1 = l1.Next
	}

	for l2 != nil {
		sum := l2.Val + carry
		carry, sum = sum/10, sum%10
		prev.Next = &ListNode{sum, nil}
		prev = prev.Next
		l2 = l2.Next
	}

	if carry != 0 {
		prev.Next = &ListNode{1, nil}
	}

	return sentinel.Next
}
