package main

func maxSubArray(nums []int) int {
	running, ans := nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		num := nums[i]
		running += num
		if running < num {
			running = num
		}
		if running > ans {
			ans = running
		}
	}
	return ans
}
