package main

/**
* 54. Spiral Matrix Medium
* https://leetcode.com/problems/spiral-matrix/
*
* Given an m x n matrix, return all elements of the matrix in spiral order.
*
* Example 1:
* Input: matrix = [[1,2,3],[4,5,6],[7,8,9]] Output: [1,2,3,6,9,8,7,4,5]
*
* Example 2:
* Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]] Output:
* [1,2,3,4,8,12,11,10,9,5,6,7]
*
* Constraints:
* m == matrix.length n == matrix[i].length 1 <= m, n <= 10 -100 <= matrix[i][j]
* <= 100
 */

func spiralOrder(mat [][]int) []int {
	rows, cols := len(mat), len(mat[0])
	ans, pos := make([]int, rows*cols), 0

	for i := 0; i <= (rows+1)/2; i++ {
		// move in right order across first row, all columns
		for col := i; col < cols-i; col++ {
			ans[pos] = mat[i][col]
			pos++
		}
		if pos >= rows*cols {
			break
		}
		// skip first row, move in correct order in last column, all rows
		for row := i + 1; row < rows-i; row++ {
			ans[pos] = mat[row][cols-i-1]
			pos++
		}
		if pos >= rows*cols {
			break
		}
		// skip the last col, move in reverse order in last row, all columns
		for col := cols - i - 2; col >= i; col-- {
			ans[pos] = mat[rows-i-1][col]
			pos++
		}
		if pos >= rows*cols {
			break
		}
		// skip last and first row, move in reverse order, in first column, all rows
		for row := rows - i - 2; row > i; row-- {
			ans[pos] = mat[row][i]
			pos++
		}
		if pos >= rows*cols {
			break
		}
	}
	return ans
}
