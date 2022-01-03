package main

/**
 * 55.
 * Jump Game
 * https://leetcode.com/problems/jump-game/
 * Medium
 *
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum
 * jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
 * to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4] Output: false Explanation: You will always arrive
 * at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 *
 * Constraints:
 * 1 <= nums.length <= 104 0 <= nums[i] <= 105
 */

type JumpGame struct{}

func (this *JumpGame) dfs(nums []int, pos int, visited []bool) bool {
	if pos >= len(nums)-1 {
		return true
	}
	if visited[pos] {
		return false
	}
	next := pos + nums[pos]
	visited[pos] = true
	for i := pos + 1; i <= next; i++ {
		if !visited[i] && this.dfs(nums, i, visited) {
			return true
		}
	}
	return false
}

func (this *JumpGame) bfs(nums []int) bool {
	size := len(nums)
	from, till := 0, 0
	for till != -1 {
		next := -1
		for i := from; i <= till; i++ {
			pos := i + nums[i]
			if pos >= size-1 {
				return true
			}
			if pos > till && pos > next {
				next = pos
			}
		}
		from, till = till+1, next
	}
	return false
}

func canJump(nums []int) bool {
	jG := &JumpGame{}
	one := jG.dfs(nums, 0, make([]bool, len(nums)))
	return one
}
