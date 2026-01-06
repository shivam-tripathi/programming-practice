package main

/*
59. Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii/
Medium

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral
order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
    1 <= n <= 20
*/

func generateMatrix(n int) [][]int {
	mat := make([][]int, n)
	for i := 0; i < n; i++ {
		mat[i] = make([]int, n)
	}

	ctr := 1
	for i := 0; i < n/2; i++ {
		for j := i; j < n-i; j++ {
			mat[i][j] = ctr
			ctr++
		}

		for j := i + 1; j < n-i; j++ {
			mat[j][n-i-1] = ctr
			ctr++
		}

		for j := n - i - 2; j >= i; j-- {
			mat[n-i-1][j] = ctr
			ctr++
		}

		for j := n - i - 2; j >= i+1; j-- {
			mat[j][i] = ctr
			ctr++
		}

	}

	if mat[n/2][n/2] == 0 {
		mat[n/2][n/2] = ctr
	}

	return mat
}
