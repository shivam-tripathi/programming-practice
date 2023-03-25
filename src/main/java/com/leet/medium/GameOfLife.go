package main

func gameOfLife(board [][]int) {
	rows, cols := len(board), len(board[0])
	cur, prev := make([]int, cols), make([]int, cols)

	for x := 0; x < rows; x++ {
		for y := 0; y < cols; y++ {
			elem, live := board[x][y], 0
			for rmp := -1; rmp <= 1; rmp++ {
				for cmp := -1; cmp <= 1; cmp++ {
					if rmp == 0 && cmp == 0 {
						continue
					}
					if x+rmp >= 0 && y+cmp >= 0 && x+rmp < rows && y+cmp < cols {
						live += board[x+rmp][y+cmp]
					}
				}
			}

			if elem == 1 && live < 2 {
				cur[y] = 0
			} else if elem == 1 && live >= 2 && live <= 3 {
				cur[y] = 1
			} else if elem == 1 && live > 3 {
				cur[y] = 0
			} else if elem == 0 && live == 3 {
				cur[y] = 1
			} else {
				cur[y] = elem
			}
		}

		if x > 0 {
			for i := 0; i < cols; i++ {
				prev[i], board[x-1][i] = board[x-1][i], prev[i]
			}
		}

		prev, cur = cur, prev
	}

	for i := 0; i < cols; i++ {
		prev[i], board[rows-1][i] = board[rows-1][i], prev[i]
	}
}
