package main

func numberOfArithmeticSlices(nums []int) int {
	if len(nums) < 3 {
		return 0
	}

	size, diff, ans := 2, nums[1]-nums[0], 0

	for i := 2; i < len(nums); i++ {
		curDiff := nums[i] - nums[i-1]
		if diff == curDiff {
			size += 1
		} else {
			if size >= 3 {
				ans += size*(size+1)/2 - 2*size + 1
			}
			size = 2
			diff = curDiff
		}
	}

	if size >= 3 {
		ans += size*(size+1)/2 - 2*size + 1
	}

	return ans
}
