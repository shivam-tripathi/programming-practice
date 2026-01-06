package main

/**
 * 437. Path Sum III Medium
 *
 * https://leetcode.com/problems/path-sum-iii/
 *
 * Given the root of a binary tree and an integer targetSum, return the number
 * of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 *
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000]. -109 <= Node.val
 * <= 109 -1000 <= targetSum <= 1000
 */

type PathSumIII struct {
	targetSum int
}

func (this *PathSumIII) merge(arr, next []int, pos int, node *TreeNode) (int, int) {
	ptr, ans, compl := 0, 0, this.targetSum-node.Val
	for ptr < len(arr) {
		next[pos] = arr[ptr] + node.Val
		if arr[ptr] == compl {
			ans++
		}
		ptr++
		pos++
	}
	return pos, ans
}

func (this *PathSumIII) solve(node *TreeNode) ([]int, int) {
	if node == nil {
		return []int{}, 0
	}
	left, lans := this.solve(node.Left)
	right, rans := this.solve(node.Right)
	next := make([]int, len(left)+len(right)+1)
	var mergeLeftAns, mergeRightAns, pos int
	pos, mergeLeftAns = this.merge(left, next, 0, node)
	pos, mergeRightAns = this.merge(right, next, pos, node)
	ans := mergeLeftAns + mergeRightAns
	if node.Val == this.targetSum {
		ans++
	}
	next[pos] = node.Val
	return next, ans + lans + rans
}

func pathSum(root *TreeNode, targetSum int) int {
	sol := PathSumIII{targetSum}
	_, ans := sol.solve(root)
	return ans
}
