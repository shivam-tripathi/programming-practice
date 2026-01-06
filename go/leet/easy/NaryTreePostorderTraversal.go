package main

/**
 * Definition for a Node.
 */

type NaryNode struct {
	Val      int
	Children []*NaryNode
}

type NaryNodePostOrderTraversal struct{}

func (this *NaryNodePostOrderTraversal) solve(node *NaryNode, ans []int) []int {
	if node == nil {
		return ans
	}
	for _, child := range node.Children {
		ans = this.solve(child, ans)
	}
	ans = append(ans, node.Val)
	return ans
}

func (this *NaryNodePostOrderTraversal) postorder(root *NaryNode) []int {
	return this.solve(root, []int{})
}
