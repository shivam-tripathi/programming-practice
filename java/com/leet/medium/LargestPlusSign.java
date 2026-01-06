package com.leet.medium;

/**
 * 764. Largest Plus Sign Medium
 *
 * https://leetcode.com/problems/largest-plus-sign/
 *
 * You are given an integer n. You have an n x n binary grid grid with all
 * values initially 1's except for some indices given in the array mines. The
 * ith element of the array mines is defined as mines[i] = [xi, yi] where
 * grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in
 * grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1
 * along with four arms of length k - 1 going up, down, left, and right, and
 * made of 1's. Note that there could be 0's or 1's beyond the arms of the plus
 * sign, only the relevant area of the plus sign is checked for 1's.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, mines = [[4,2]] Output: 2 Explanation: In the above grid, the
 * largest plus sign can only be of order 2. One of them is shown.
 *
 * Example 2:
 *
 * Input: n = 1, mines = [[0,0]] Output: 0 Explanation: There is no plus sign,
 * so return 0.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500 1 <= mines.length <= 5000 0 <= xi, yi < n All the pairs (xi,
 * yi) are unique.
 */

class Node {
	boolean mine;
	int left;
	int right;
	int top;
	int bottom;

	public String toString() {
		return String.format("{%d,%d,%d,%d}", left, right, top, bottom);
	}
}

public class LargestPlusSign {
	public int solve(Node[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (!matrix[i][j].mine) {
					int left = j == 0 ? 0 : matrix[i][j - 1].left;
					int top = i == 0 ? 0 : matrix[i - 1][j].top;
					matrix[i][j].left = 1 + left;
					matrix[i][j].top = 1 + top;
				}
			}
		}

		for (int i = matrix.length - 1; i >= 0; i--) {
			for (int j = matrix.length - 1; j >= 0; j--) {
				if (!matrix[i][j].mine) {
					int right = j == matrix.length - 1 ? 0 : matrix[i][j + 1].right;
					int bottom = i == matrix.length - 1 ? 0 : matrix[i + 1][j].bottom;
					matrix[i][j].right = right + 1;
					matrix[i][j].bottom = bottom + 1;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				Node node = matrix[i][j];
				int order = Math.min(node.left, Math.min(node.right, Math.min(node.top, node.bottom)));
				ans = Math.max(ans, order);
			}
		}

		return ans;
	}

	public int orderOfLargestPlusSign(int n, int[][] mines) {
		Node[][] matrix = new Node[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = new Node();
			}
		}
		for (int[] mine : mines) {
			matrix[mine[0]][mine[1]].mine = true;
		}
		return solve(matrix);
	}
}
