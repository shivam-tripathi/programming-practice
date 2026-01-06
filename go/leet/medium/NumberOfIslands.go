package main

func mark(grid [][]byte, row, col int) {
	if row >= len(grid) || row < 0 || col >= len(grid[row]) || col < 0 || grid[row][col] != '1' {
		return
	}
	grid[row][col] = '0'
	mark(grid, row+1, col)
	mark(grid, row-1, col)
	mark(grid, row, col+1)
	mark(grid, row, col-1)
}

func numIslands(grid [][]byte) int {
	ans := 0
	for row, _row := range grid {
		for col, elem := range _row {
			if elem == '1' {
				ans++
				mark(grid, row, col)
			}
		}
	}
	return ans
}
