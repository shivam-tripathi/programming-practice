package main

func splitListToParts(head *ListNode, k int) []*ListNode {
	nodes := make([]*ListNode, k)

	// First, calculate the size of the list
	size, node := 0, head
	for node != nil {
		node = node.Next
		size++
	}

	node = head
	pos, batchSize := 0, size/k
	// Elements in batch should be atleast 1
	if batchSize == 0 {
		batchSize = 1
	}

	for pos < k && node != nil {
		// first size%k elements must have an extra element
		items := batchSize
		if size >= k && pos < size%k {
			items += 1
		}

		// Iterate over the nodes and extract first `items` nodes
		prev, cur := node, node
		for cur != nil && items != 0 {
			prev = cur
			cur = cur.Next
			items--
		}

		// Save the head in the array
		nodes[pos] = node
		if prev != nil {
			prev.Next = nil
		}

		node = cur
		pos++
	}
	return nodes
}
