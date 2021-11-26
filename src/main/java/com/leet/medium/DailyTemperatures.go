/*
739. Daily Temperatures
https://leetcode.com/problems/daily-temperatures/
Medium

Given an array of integers temperatures represents the daily temperatures, return an array answer
such that answer[i] is the number of days you have to wait after the ith day to get a warmer
temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:
    1 <= temperatures.length <= 105
    30 <= temperatures[i] <= 100
*/

func dailyTemperatures(temperatures []int) []int {
	stack := make([][2]int, len(temperatures))
	ans := make([]int, len(temperatures))
	size := 0

	for i := len(temperatures) - 1; i >= 0; i-- {
		temperature := temperatures[i]
		for size != 0 && stack[size-1][0] <= temperature {
			size--
		}
		if size != 0 {
			ans[i] = stack[size-1][1] - i
		} else {
			ans[i] = 0
		}
		stack[size][0], stack[size][1] = temperature, i
		size++
	}

	return ans
}
