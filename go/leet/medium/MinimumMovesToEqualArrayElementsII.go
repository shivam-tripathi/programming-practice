package main

import "sort"

func minMoves2(nums []int) int {
	sort.Ints(nums)
	median := nums[len(nums)/2]
	if len(nums)%2 != 1 {
		median = (median + nums[len(nums)/2-1]) / 2
	}
	ans := 0
	for _, item := range nums {
		val := item - median
		if val < 0 {
			ans += -val
		} else {
			ans += val
		}
	}
	return ans
}
