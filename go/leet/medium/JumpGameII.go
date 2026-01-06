package main

/**
 * 45. Jump Game II Medium
 *
 * https://leetcode.com/problems/jump-game-ii/
 *
 * Given an array of non-negative integers nums, you are initially positioned at
 * the first index of the array. Each element in the array represents your
 * maximum jump length at that position. Your goal is to reach the last index in
 * the minimum number of jumps. You can assume that you can always reach the
 * last index.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps
 * to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to
 * the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4] Output: 2
 *
 * Constraints: 1 <= nums.length <= 104 0 <= nums[i] <= 1000
 */

func jump(nums []int) int {
	if len(nums) <= 1 {
		return 0
	}
	// Think in terms of buckets: I can reach anywhere [start, end] in one jump
	// I would have to determine the furthest I can go from this range
	// Repeat till I hit the last position
	start, end, jumps := 1, 0+nums[0], 1
	for end < len(nums)-1 {
		farthest := start + nums[start]
		for i := start; i <= end; i++ {
			if (i + nums[i]) > farthest {
				farthest = i + nums[i]
			}
		}
		start, end, jumps = end+1, farthest, jumps+1
	}
	return jumps
}
