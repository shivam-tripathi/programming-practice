package main

/**
 * Definition for a Node.
 */

type NAryNode struct {
	Val       int
	Neighbors []*NAryNode
}

func cloneGraphUtil(node *NAryNode, mapping []*NAryNode) *NAryNode {
	if node == nil {
		return nil
	}

	if cloned := mapping[node.Val]; cloned != nil {
		return cloned
	}

	clone := &NAryNode{Val: node.Val, Neighbors: make([]*NAryNode, len(node.Neighbors))}
	mapping[node.Val] = clone

	for i, neighbor := range node.Neighbors {
		clone.Neighbors[i] = cloneGraphUtil(neighbor, mapping)
	}

	return clone
}

func cloneGraph(node *NAryNode) *NAryNode {
	mapping := make([]*NAryNode, 100+1)
	ans := cloneGraphUtil(node, mapping)
	return ans
}
