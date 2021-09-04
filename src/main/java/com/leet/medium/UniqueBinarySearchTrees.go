package main

/*
96. Unique Binary Search Trees
https://leetcode.com/problems/unique-binary-search-trees/
Medium

Given an integer n, return the number of structurally unique BST's (binary search trees) which has
exactly n nodes of unique values from 1 to n.

Example 1:
Input: n = 3
Output: 5

Example 2:
Input: n = 1
Output: 1

Constraints:
	1 <= n <= 19
*/

func solve(start, end int, dp map[int]int) int {
	if start > end {
		return 1
	}

	if start == end {
		return 1
	}

	if val, ok := dp[end-start+1]; ok {
		return val
	}

	ans := 0
	for i := start; i <= end; i++ {
		ans += solve(start, i-1, dp) * solve(i+1, end, dp)
	}

	dp[end-start+1] = ans

	return ans
}

func numTrees(n int) int {
	return solve(1, n, map[int]int{})
}
