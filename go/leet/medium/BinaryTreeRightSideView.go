package main

func BinaryTreeRightSideViewUtil(node *TreeNode, view []int, curDepth int, maxDepth int) ([]int, int) {
	if node == nil {
		return view, curDepth - 1
	}

	if curDepth > maxDepth {
		view = append(view, node.Val)
	}

	view, right := BinaryTreeRightSideViewUtil(node.Right, view, curDepth+1, maxDepth)
	if right > maxDepth {
		maxDepth = right
	}

	view, left := BinaryTreeRightSideViewUtil(node.Left, view, curDepth+1, maxDepth)
	if left > maxDepth {
		maxDepth = left
	}

	return view, maxDepth
}

func rightSideView(root *TreeNode) []int {
	view, _ := BinaryTreeRightSideViewUtil(root, []int{}, 1, 0)
	return view
}
