package main

import "sort"

func nextPermutation(nums []int) {
	if len(nums) <= 1 {
		return
	}
	size, pos := len(nums), -1
	for i := size - 2; i >= 0; i-- {
		if nums[i] < nums[i+1] {
			pos = i
			break
		}
	}
	if pos == -1 {
		sort.Ints(nums)
		return
	}
	minPos := pos + 1
	for i := pos + 1; i < size; i++ {
		if nums[i] > nums[pos] && nums[minPos] > nums[i] {
			minPos = i
		}
	}
	nums[minPos], nums[pos] = nums[pos], nums[minPos]
	sort.Ints(nums[pos+1:])
}
