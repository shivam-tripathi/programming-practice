package main

import "sort"

func merge(intervals [][]int) [][]int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})

	begin, end := intervals[0][0], intervals[0][1]
	ans := [][]int{}
	for _, interval := range intervals {
		if end < interval[0] {
			ans = append(ans, []int{begin, end})
			begin, end = interval[0], interval[1]
		} else {
			if interval[1] > end {
				end = interval[1]
			}
		}
	}

	return append(ans, []int{begin, end})
}
