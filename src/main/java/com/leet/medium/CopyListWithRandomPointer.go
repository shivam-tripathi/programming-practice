package main

/*
 * Definition for a Node.
 */
type RandomNode struct {
	Val    int
	Next   *RandomNode
	Random *RandomNode
}

/*
 * Handle random pointer in a separate iteration - else you might end up messing with the
 * origin list node
 */

func copyRandomList(head *RandomNode) *RandomNode {
	if head == nil {
		return nil
	}

	node := head
	for node != nil {
		node.Next = &RandomNode{node.Val, node.Next, node.Random}
		node = node.Next.Next
	}

	node = head
	for node != nil {
		cpy := node.Next
		if cpy.Random != nil {
			cpy.Random = cpy.Random.Next
		}
		node = cpy.Next
	}

	cpyHead := head.Next

	node = head
	for node != nil {
		cpy, next := node.Next, node.Next.Next
		if next != nil {
			cpy.Next = next.Next
		}
		node.Next = next
		node = next
	}

	return cpyHead
}
