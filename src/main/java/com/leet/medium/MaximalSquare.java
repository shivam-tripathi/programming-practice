package com.leet.medium;

/**
 * 221.
 *
 * Maximal Square Medium
 *
 * https://leetcode.com/problems/maximal-square/
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 *
 * Example 1: Input: matrix =
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2: Input: matrix = [["0","1"],["1","0"]] Output: 1
 *
 * Example 3: Input: matrix = [["0"]] Output: 0
 *
 * Constraints: m == matrix.length n == matrix[i].length 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'
 */

public class MaximalSquare {
	public int maximalSquare(char[][] inp) {
		int rows = inp.length, cols = inp[0].length;

		int[] prev = new int[cols + 1];
		int[] curr = new int[cols + 1];

		int ans = 0;

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (inp[i - 1][j - 1] == '1') {
					curr[j] = Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1])) + 1;
					ans = Math.max(ans, curr[j]);
				} else {
					curr[j] = 0;
				}
			}

			// swap
			int[] tmp = prev;
			prev = curr;
			curr = tmp;
		}

		return ans * ans;
	}
}
