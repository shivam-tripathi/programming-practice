package main

type ReorderList struct {
	head *ListNode
}

func (this *ReorderList) solveRecur(cur, head *ListNode) *ListNode {
	if cur == nil {
		return head
	}

	prev := this.solveRecur(cur.Next, head)
	if prev == nil {
		return nil
	}

	// don't process same node twice - odd size list
	if prev == cur {
		cur.Next = nil
		return nil
	}

	next := prev.Next
	prev.Next = cur
	cur.Next = next

	// don't process same node twice - even size list
	if next == cur {
		cur.Next = nil
		return nil
	}

	return next
}

func (this *ReorderList) solveIter(head *ListNode) {
	size, node := 0, head
	for node != nil {
		size++
		node = node.Next
	}

}

func reorderList(head *ListNode) {
	solution := &ReorderList{head}
	solution.solveRecur(head, head)
}
