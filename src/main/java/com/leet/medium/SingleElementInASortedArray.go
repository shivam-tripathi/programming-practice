package main

/*
540. Single Element in a Sorted Array
https://leetcode.com/problems/single-element-in-a-sorted-array/
Medium

You are given a sorted array consisting of only integers where every element appears exactly twice,
except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: nums = [3,3,7,7,10,11,11]
Output: 10

Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] <= 105
*/

func singleNonDuplicate(nums []int) int {
	start, stop := 0, len(nums)-1
	for start < stop {
		mid := (start + stop) / 2
		if nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1] {
			return nums[mid]
		}
		if nums[mid] == nums[mid+1] {
			if mid%2 == 0 {
				start = mid + 2
			} else {
				stop = mid - 1
			}
		} else if nums[mid] == nums[mid-1] {
			if mid%2 == 0 {
				stop = mid - 2
			} else {
				start = mid + 1
			}
		}
	}
	return nums[start]
}
