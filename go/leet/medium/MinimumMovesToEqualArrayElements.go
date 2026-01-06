package main

import (
	"fmt"
	"sort"
)

func minMoves(nums []int) int {
	ops := 0
	for true {
		off := false
		sort.Ints(nums)
		fmt.Println(nums)
		for i := 1; i < len(nums); i++ {
			if nums[i] != nums[i-1] {
				off = true
				break
			}
		}
		if !off {
			break
		}
		for i := 0; i < len(nums)-1; i++ {
			nums[i]++
		}
		ops++
	}
	fmt.Println(nums)
	return ops
}

func main() {
	arr := []int{1, 3, 5, 9}
	ans := minMoves(arr)
	fmt.Println(ans)
}
