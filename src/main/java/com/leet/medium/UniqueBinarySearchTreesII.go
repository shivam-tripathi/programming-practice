package main

/*
95. Unique Binary Search Trees II
https://leetcode.com/problems/unique-binary-search-trees-ii/
Medium

Given an integer n, return all the structurally unique BST's (binary search trees), which has
exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
    1 <= n <= 8
*/

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func solve(start, end int) []*TreeNode {
	if start > end {
		return []*TreeNode{nil}
	}

	if end == start {
		return []*TreeNode{{Val: start}}
	}

	ans := []*TreeNode{}
	for i := start; i <= end; i++ {
		leftNodes := solve(start, i-1)
		rightNodes := solve(i+1, end)
		for _, left := range leftNodes {
			for _, right := range rightNodes {
				node := &TreeNode{Val: i, Left: left, Right: right}
				ans = append(ans, node)
			}
		}
	}

	return ans
}

func generateTrees(n int) []*TreeNode {
	return solve(1, n)
}
