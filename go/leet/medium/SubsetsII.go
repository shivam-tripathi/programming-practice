package main

import "sort"

func subsetsSolve(nums []int, pos int, size int, set []int, ans [][]int) [][]int {
	if pos == size {
		collect := make([]int, len(set))
		copy(collect, set)
		return append(ans, collect)
	}

	ans = subsetsSolve(nums, pos+1, size, set, ans)
	set = append(set, nums[pos])
	return subsetsSolve(nums, pos+1, size, set, ans)
}

func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	slow, fast := 0, 1
	for fast < len(nums) {
		if nums[fast] != nums[fast-1] {
			nums[slow+1] = nums[fast]
			slow++
		}
		fast++
	}
	return subsetsSolve(nums, 0, slow, []int{}, [][]int{})
}
