package main

/*
1658. Minimum Operations to Reduce X to Zero
https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/description/
Medium

You are given an integer array nums and an integer x. In one operation, you can either remove the
leftmost or the rightmost element from the array nums and subtract its value from x. Note that this
modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise,
return -1.



Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements
(5 operations in total) to reduce x to zero.


Constraints:
	1 <= nums.length <= 105
	1 <= nums[i] <= 104
	1 <= x <= 109
*/

func minOperationsToReduceXToZero(nums []int, x int) int {
	left, right, running := 0, 0, 0
	sum := 0
	for _, num := range nums {
		sum += num
	}

	target := sum - x

	if target < 0 {
		return -1
	}

	if target == 0 {
		return len(nums)
	}

	diff := -1

	for right < len(nums) {
		for right < len(nums) && running+nums[right] <= target {
			running += nums[right]
			right += 1
		}
		if running == target && (diff == -1 || diff < (right-left)) {
			diff = right - left
		}
		running -= nums[left]
		left += 1
	}

	if diff == -1 {
		return diff
	}

	return len(nums) - diff
}
