package main

/*
52. N-Queens II
https://leetcode.com/problems/n-queens-ii/
Hard

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two
queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Example 2:
Input: n = 1
Output: 1

Constraints:
	1 <= n <= 9
*/

type TotalNQueens struct {
	size  int
	ans   int
	board []int
}

func (this *TotalNQueens) isValid(row, col int) bool {
	for i := 0; i < row; i++ {
		diff := col - this.board[i]
		if col < this.board[i] {
			diff = this.board[i] - col
		}
		if this.board[i] == col || (row-i) == diff {
			return false
		}
	}
	return true
}

func (this *TotalNQueens) backtrack(row int) {
	if row >= this.size {
		this.ans += 1
	} else {
		for i := 0; i < this.size; i++ {
			if this.isValid(row, i) {
				this.board[row] = i
				this.backtrack(row + 1)
			}
		}
	}
}

func totalNQueens(n int) int {
	solution := TotalNQueens{n, 0, make([]int, n)}
	solution.backtrack(0)
	return solution.ans
}

// func main() {
// 	ans := []int{
// 		0, 1, 0, 0, 2, 10,
// 		4, 40, 92, 352,
// 	}
// 	for i := 1; i < 10; i++ {
// 		fmt.Println(totalNQueens(i) == ans[i])
// 	}
// }
