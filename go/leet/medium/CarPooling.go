package main

func carPooling(trips [][]int, capacity int) bool {
	pos := [1001]int{}
	for _, trip := range trips {
		pos[trip[1]] += trip[0]
		pos[trip[2]] -= trip[0]
	}

	load := 0
	for _, cur := range pos {
		load += cur
		if load > capacity {
			return false
		}
	}
	return true
}
