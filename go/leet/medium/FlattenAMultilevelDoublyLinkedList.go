package main

func flatten(_node *StackNode) *StackNode {
	type Node struct {
		Val   int
		Prev  *Node
		Next  *Node
		Child *Node
	}

	var flattenUtil func(node *Node, tail *Node) *Node

	(func(node *Node, tail *Node) *Node {
		if node == nil {
			return tail
		}
		tail = flattenUtil(node.Next, tail)
		tail = flattenUtil(node.Child, tail)
		node.Next = tail
		if tail != nil {
			tail.Prev = node
		}
		node.Child = nil
		return node
	})(_node, nil)
	// return flattenUtil(node, nil)
}
