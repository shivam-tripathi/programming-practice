package com.leet.hard;

/**
 * 1289. Minimum Falling Path Sum II
 *
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 *
 * Hard
 *
 * Given an n x n integer matrix grid, return the minimum sum of a falling path
 * with non-zero shifts.
 *
 * A falling path with non-zero shifts is a choice of exactly one element from
 * each row of grid such that no two elements chosen in adjacent rows are in the
 * same column.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]] Output: 13
 *
 * Explanation: The possible falling paths are: [1,5,9], [1,5,7], [1,6,7],
 * [1,6,8], [2,4,8], [2,4,9], [2,6,7], [2,6,8], [3,4,8], [3,4,9], [3,5,7],
 * [3,5,9] The falling path with the smallest sum is [1,5,7], so the answer is
 * 13.
 *
 * Example 2: Input: grid = [[7]] Output: 7
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length 1 <= n <= 200 -99 <= grid[i][j] <= 99
 */

public class MinimumFallingPathSumII {
	// find minimum for each row, add it current col (if it's same col add second
	// minimum)
	public int minFallingPathSum(int[][] grid) {
		int rows = grid.length, cols = grid[0].length;

		if (rows == 1) {
			return grid[0][0];
		}

		int min = 0, secondMin = 0;
		for (int row = rows - 2; row >= 0; row--) {
			int[] cur = grid[row];
			int[] prev = grid[row + 1];

			min = prev[0] < prev[1] ? prev[0] : prev[1];
			secondMin = prev[0] < prev[1] ? prev[1] : prev[0];
			for (int i = 2; i < cols; i++) {
				if (prev[i] < min) {
					secondMin = min;
					min = prev[i];
				} else if (prev[i] < secondMin) {
					secondMin = prev[i];
				}
			}

			for (int col = 0; col < cols; col++) {
				cur[col] += grid[row + 1][col] == min ? secondMin : min;
			}
		}

		min = Integer.MAX_VALUE;
		for (int i = 0; i < cols; i++) {
			min = Math.min(min, grid[0][i]);
		}
		return min;
	}
}
