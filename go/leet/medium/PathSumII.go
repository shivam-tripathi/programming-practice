package main

type PathSumII struct{}

// Gotcha - test and add to result explicitly checking for leaf. Nil nodes are misleading and
// can lead to multiple addition for same path

// Also append in go is NOT IMMUTABLE action. It will not create a new slice.

func (this *PathSumII) solve(node *TreeNode, ans [][]int, path []int, sum, target int) [][]int {
	if node == nil {
		return ans
	}
	sum += node.Val
	path = append(path, node.Val)

	// is leaf
	if node.Left == nil && node.Right == nil && sum == target {
		clone := make([]int, len(path))
		copy(clone, path)
		ans = append(ans, clone)
	} else {
		ans = this.solve(node.Left, ans, path, sum, target)
		ans = this.solve(node.Right, ans, path, sum, target)
	}
	return ans
}

func (this *PathSumII) pathSum(root *TreeNode, targetSum int) [][]int {
	if root == nil {
		return [][]int{}
	}
	return this.solve(root, [][]int{}, []int{}, 0, targetSum)
}
