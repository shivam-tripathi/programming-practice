package main

// Method 1: Recursive. Idiotic to use stack if you're using linked list.
type State struct {
	Pos  int
	Node *ListNode
}

func remove(node *ListNode, n int) *State {
	if node == nil {
		return &State{Pos: 1, Node: nil}
	}
	state := remove(node.Next, n)
	if state.Pos == n {
		state.Pos += 1
		return state
	}
	state.Pos++
	node.Next = state.Node
	state.Node = node
	return state
}

func removeNthFromEndRecursive(head *ListNode, n int) *ListNode {
	sentinel := &ListNode{Val: 0, Next: head}
	remove(sentinel, n)
	return sentinel.Next
}

// Method 2: Slow and fast pointer.
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	sentinel := &ListNode{0, head}
	fast, slow, prev := sentinel, sentinel, sentinel
	for i := 1; i <= n; i++ {
		fast = fast.Next
	}
	for fast != nil {
		fast = fast.Next
		prev = slow
		slow = slow.Next
	}
	prev.Next = slow.Next
	return sentinel.Next
}
