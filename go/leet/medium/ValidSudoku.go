package main

/*
36. Valid Sudoku
https://leetcode.com/problems/valid-sudoku/
Medium

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to
the following rules:
    Each row must contain the digits 1-9 without repetition.
    Each column must contain the digits 1-9 without repetition.
    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:
    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    Only the filled cells need to be validated according to the mentioned rules.

Example 1:
Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true

Example 2:
Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since
there are two 8's in the top left 3x3 sub-box, it is invalid.

Constraints:
    board.length == 9
    board[i].length == 9
    board[i][j] is a digit 1-9 or '.'.
*/

func isValidSudoku(board [][]byte) bool {
	// we keep track of which numbers in the rows, cols and blocks we have set
	// if there is a repetition, we return false

	rset, cset, bset := [9][9]bool{}, [9][9]bool{}, [9][9]bool{}

	for i := 0; i < 9; i++ {
		for j := 0; j < 9; j++ {
			if item := board[i][j]; item >= '1' && item <= '9' {
				num := int(item - '1')
				if rset[i][num] {
					return false
				}
				if cset[j][num] {
					return false
				}
				if bset[(i/3)*3+(j/3)][num] {
					return false
				}
				rset[i][num] = true
				cset[j][num] = true
				bset[(i/3)*3+(j/3)][num] = true
			}
		}
	}

	return true
}
