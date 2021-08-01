package main

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func reverse(node *ListNode, size, k int) (*ListNode, *ListNode) {
	if size < k {
		return node, nil
	}

	var newHead, nextTail, prev *ListNode
	for node != nil {
		next := node.Next
		node.Next = prev
		prev = node
		node = next
		k--
		if k == 0 {
			newHead = prev
			nextTail = node
			break
		}
	}
	return newHead, nextTail
}

func reverseKGroup(head *ListNode, k int) *ListNode {
	// First calculate size
	size, node := 0, head
	for node != nil {
		node = node.Next
		size += 1
	}

	// Next, reverse in batches. Every batch reverse starts with tail of cur batch
	// At the end of batch, we get head of cur batch, as well as tail of next batch
	// In case size of listnode is smaller than batch size, we do not reverse
	// To ensure this, we keep track of current position in the list, size - cur pos = remaining size
	curTail, sentinel, pos := head, &ListNode{}, 0
	prevTail := sentinel
	for curTail != nil {
		curHead, nextTail := reverse(curTail, size-pos, k)
		prevTail.Next = curHead
		prevTail = curTail
		curTail = nextTail
		pos += k
	}

	return sentinel.Next
}
