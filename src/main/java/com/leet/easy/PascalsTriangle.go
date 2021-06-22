package main

func getValue(row []int, pos int) int {
	if pos < 0 || pos >= len(row) {
		return 0
	}
	return row[pos]
}

func generate(numRows int) [][]int {
	ans := [][]int{[]int{1}}
	for i := 1; i < numRows; i++ {
		prevRow := ans[i-1]
		nextRow := make([]int, len(prevRow)+1)
		for col := 0; col <= len(prevRow); col++ {
			a, b := getValue(prevRow, col-1), getValue(prevRow, col)
			nextRow[col] = a + b
		}
		ans = append(ans, nextRow)
	}
	return ans
}
