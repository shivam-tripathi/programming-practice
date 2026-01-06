package main

/*
228. Summary Ranges
https://leetcode.com/problems/summary-ranges/
Easy

You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is,
each element of nums is covered by exactly one of the ranges, and there is no integer x such that x
is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

    "a->b" if a != b
    "a" if a == b

Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"

Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"

Constraints:
    0 <= nums.length <= 20
    -231 <= nums[i] <= 231 - 1
    All the values of nums are unique.
    nums is sorted in ascending order.
*/

import "fmt"

func rangeToString(a, b int) string {
	if a != b {
		return fmt.Sprintf("%d->%d", a, b)
	}
	return fmt.Sprintf("%d", a)
}

func summaryRanges(nums []int) []string {
	ans := []string{}

	if len(nums) == 0 {
		return ans
	}

	l, r := 0, 0
	for r < len(nums)-1 {
		if nums[r]+1 >= nums[r+1] {
			r++
		} else {
			ans = append(ans, rangeToString(nums[l], nums[r]))
			r++
			l = r
		}
	}

	ans = append(ans, rangeToString(nums[l], nums[r]))

	return ans
}
