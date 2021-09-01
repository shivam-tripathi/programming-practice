package main

/*
73. Set Matrix Zeroes
https://leetcode.com/problems/set-matrix-zeroes/
Medium

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and
return the matrix.

You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:
    m == matrix.length
    n == matrix[0].length
    1 <= m, n <= 200
    -231 <= matrix[i][j] <= 231 - 1

Follow up:
    A straightforward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
*/

func setZeroes(matrix [][]int) {
	rows, cols := len(matrix), len(matrix[0])
	type Record struct {
		Rows map[int]bool
		Cols map[int]bool
	}

	record := Record{map[int]bool{}, map[int]bool{}}

	for i := 0; i < rows; i++ {
		for j := 0; j < cols; j++ {
			if matrix[i][j] == 0 {
				record.Rows[i] = true
				record.Cols[j] = true
			}
		}
	}

	for i := 0; i < rows; i++ {
		for j := 0; j < cols; j++ {
			if record.Rows[i] || record.Cols[j] {
				matrix[i][j] = 0
			}
		}
	}
}
